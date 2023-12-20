package com.maoxian.service.impl;

import com.maoxian.config.SchedulerConfig;
import com.maoxian.exception.BusinessException;
import com.maoxian.exception.SystemException;
import com.maoxian.mapper.ScheduleRecordMapper;
import com.maoxian.mapper.RequestRecordMapper;
import com.maoxian.mapper.WafMapper;
import com.maoxian.pojo.RequestRecord;
import com.maoxian.pojo.ScheduleRecord;
import com.maoxian.pojo.Waf;
import com.maoxian.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author Lin
 * @date 2023/12/17 23:03
 */
@Service
public class SchedulerServiceImpl2 implements SchedulerService {

    private static final int NUM_THREADS = 10;
    private static final long TIMEOUT_SECONDS = 3;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WafMapper wafMapper;

    @Autowired
    private RequestRecordMapper requestRecordMapper;

    @Autowired
    private ScheduleRecordMapper scheduleRecordMapper;

    @Autowired
    private SchedulerConfig schedulerConfig;

    private final Map<String, List<Waf>> requestPool = new HashMap<>();

    @Override
    public ResponseEntity<String> serialForward(HttpServletRequest request, RequestEntity<String> requestEntity) {
        LocalDateTime startTime = LocalDateTime.now();

        // 生成uuid，用于唯一标识请求
        String uuid = UUID.randomUUID().toString();

        // 获取请求方法，请求头和请求体
        HttpMethod method = requestEntity.getMethod();
        if (method == null) {
            method = HttpMethod.GET;
        }

        // 获取客户端地址
        String sourceIp = request.getRemoteAddr();

        // 获取请求主机和URI
        URI url = requestEntity.getUrl();

        // 将此次请求存到数据库
        RequestRecord requestRecord = new RequestRecord(
                null, uuid, method.name(), sourceIp, url.getHost(), url.getPath(), schedulerConfig.getSchedulerMode(),
                0L, Boolean.TRUE, null, null
        );
        requestRecordMapper.insert(requestRecord);
        requestRecord = requestRecordMapper.select(uuid);

        // 获取此次请求选择的waf
        List<Waf> selectWafs = selectWafs();

        HttpHeaders headers = requestEntity.getHeaders();
        String body = requestEntity.getBody();

        boolean pass = true;

        // 依次将请求转发到选择的waf
        ResponseEntity<String> response;
        for (Waf selectWaf : selectWafs) {
            long startTime2 = System.currentTimeMillis();
            response = restTemplate.exchange(
                    selectWaf + url.getPath(), method,
                    new HttpEntity<>(body, headers),
                    String.class
            );
            long time = System.currentTimeMillis() - startTime2;

            // 请求是否被拦截
            pass = response.getStatusCode().value() == 200;

            //将此次waf处理的记录存储到数据库
            ScheduleRecord record = new ScheduleRecord(null, time, pass, selectWaf.getId(), requestRecord.getId(), null, null);
            scheduleRecordMapper.insert(record);

            if (pass) {
                break;
            }
        }

        // 更新数据库中的请求时间和请求状态
        long time = System.currentTimeMillis() - startTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        requestRecord.setTime(time);
        requestRecord.setPass(pass);
        requestRecordMapper.update(requestRecord);

        // 处理完后，将请求转发到目的地址，并将结果返回
        return restTemplate.exchange(url, method, new HttpEntity<>(body, headers), String.class);
    }


    @Override
    public ResponseEntity<String> parallelForward(HttpServletRequest request, RequestEntity<String> requestEntity) throws InterruptedException, ExecutionException {
        LocalDateTime startTime = LocalDateTime.now();

        // 生成uuid，用于唯一标识请求
        String uuid = UUID.randomUUID().toString();

        // 获取请求方法，请求头和请求体
        HttpMethod method = requestEntity.getMethod();
        if (method == null) {
            method = HttpMethod.GET;
        }

        // 获取客户端地址
        String sourceIp = request.getRemoteAddr();

        // 获取请求主机和URI
        URI url = requestEntity.getUrl();

        // 将此次请求存到数据库
        RequestRecord requestRecord = new RequestRecord(
                null, uuid, method.name(), sourceIp, url.getHost(), url.getPath(), schedulerConfig.getSchedulerMode(),
                0L, Boolean.TRUE, null, null
        );
        requestRecordMapper.insert(requestRecord);
        requestRecord = requestRecordMapper.select(uuid);

        // 获取此次请求选择的waf
        List<Waf> selectWafs = selectWafs();

        HttpHeaders headers = requestEntity.getHeaders();
        String body = requestEntity.getBody();

        boolean pass = true;

        // 将请求并行转发到选择的waf
        // 创建线程池 TODO 使用ThreadPoolExecutor创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        // 创建任务列表
        List<Callable<Boolean>> tasks = new ArrayList<>();
        for (Waf selectWaf : selectWafs) {
            HttpMethod finalMethod = method;
            tasks.add(() -> {
                long startTime2 = System.currentTimeMillis();
                ResponseEntity<String> response = restTemplate.exchange(selectWaf + url.getPath(), finalMethod, new HttpEntity<>(body, headers), String.class);
                return response.getStatusCode().value() == 200;
            });
        }
        // 执行结果并等待指定时间
        List<Future<Boolean>> futures = executorService.invokeAll(tasks, TIMEOUT_SECONDS, TimeUnit.SECONDS);

        // 处理任务结果
        for (Future<Boolean> future : futures) {
            if (future.isDone() && !future.isCancelled()) {
                pass = future.get();
            }

        }
        // 关闭线程池
        executorService.shutdown();

        // 更新数据库中的请求时间和请求状态
        long time = System.currentTimeMillis() - startTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        requestRecord.setTime(time);
        requestRecord.setPass(pass);
        requestRecordMapper.update(requestRecord);

        // 处理完后，将请求转发到目的地址，并将结果返回
        return restTemplate.exchange(url, method, new HttpEntity<>(body, headers), String.class);

    }


    // 从waf中获取阈值个waf
    private List<Waf> selectWafs() {
        Integer mode = schedulerConfig.getSchedulerMode();
        List<Waf> result = new ArrayList<>();
        // 获取waf池
        List<Waf> unSelectWafList = wafMapper.selectListForStatus(0);
        // waf池小于阈值：抛出错误
        if (unSelectWafList.size() < mode) {
            throw new BusinessException("waf数量不足");
        }

        // 随机选择
        if (mode == 0) {
            Random random = new Random();
            for (int i = 0; i < schedulerConfig.getWafThreshold(); i++) {
                int count = random.nextInt(unSelectWafList.size());
                result.add(unSelectWafList.get(count));
                unSelectWafList.remove(i);
            }
            return result;
        } else if (mode == 1) {
            // 权重选择
            // 按照权重降序排序
            unSelectWafList.sort(Comparator.comparingInt(Waf::getWeight).reversed());
            return unSelectWafList.subList(0, schedulerConfig.getWafThreshold());
        }
        throw new SystemException("waf选择模式错误");
    }

    private Waf randomSelect(List<Waf> unSelectWafList) {
        int size = unSelectWafList.size();
        return unSelectWafList.get(new Random().nextInt(size));
    }

    private List<Waf> removeWafDuplicates(List<Waf> wafList) {
        // 获取waf池
        List<Waf> unSelectWafList = wafMapper.selectListForStatus(0);
        for (Waf waf : wafList) {
            for (int j = 0; j < unSelectWafList.size(); j++) {
                if (unSelectWafList.get(j).equals(waf)) {
                    // 移除下标为j的元素
                    unSelectWafList.remove(j);
                    break;
                }
            }
        }
        if (unSelectWafList.isEmpty()) {
            throw new SystemException("waf数量不足");
        }
        return unSelectWafList;
    }

    private HttpEntity<String> modifyHeader(String remoteAddress, RequestEntity<String> requestEntity) {
        // 设置请求头"X-UUID"，用于将唯一标识符传递给下游服务
        HttpHeaders headers = new HttpHeaders();
        String uuid = UUID.randomUUID().toString();
        headers.set("X-UUID", uuid);

        // 获取当前时间的时间戳，设置请求开始时间
        String startTime = String.valueOf(System.currentTimeMillis());
        headers.set("X-Schedule-Time", startTime);

        // 获取请求头中包含的请求来源IP地址，以及服务地址，为空则设置值
        String from = headers.getFirst("X-Forwarded-For");
        String to = headers.getFirst("X-Forwarded-Host");
        if (from == null || from.isEmpty()) {
            headers.add("X-Forwarded-For", remoteAddress);
        }
        if (to == null || to.isEmpty()) {
            headers.add("X-Forwarded-Host", schedulerConfig.getServerAddress());
        }
        return new HttpEntity<>(requestEntity.getBody(), headers);
    }
}
