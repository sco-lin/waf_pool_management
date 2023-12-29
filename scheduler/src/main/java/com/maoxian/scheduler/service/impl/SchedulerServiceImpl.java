package com.maoxian.scheduler.service.impl;

import com.maoxian.scheduler.pojo.*;
import com.maoxian.scheduler.exception.SystemException;
import com.maoxian.scheduler.mapper.ScheduleRecordMapper;
import com.maoxian.scheduler.mapper.RequestRecordMapper;
import com.maoxian.scheduler.mapper.WafMapper;
import com.maoxian.scheduler.service.SchedulerService;
import com.maoxian.scheduler.service.SiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author Lin
 * @date 2023/12/17 23:03
 */
@Slf4j
@Service
public class SchedulerServiceImpl implements SchedulerService {

    @Autowired
    private WafMapper wafMapper;

    @Autowired
    private RequestRecordMapper requestRecordMapper;

    @Autowired
    private ScheduleRecordMapper scheduleRecordMapper;

    @Autowired
    private SiteService siteService;

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseEntity<String> requestHandler(String remoteAddr, RequestEntity<String> requestEntity) {
        ResponseEntity<String> response = null;
        boolean pass = false;

        // 请求开始时间
        LocalDateTime startTime = LocalDateTime.now();

        // 生成uuid，用于唯一标识请求
        String uuid = UUID.randomUUID().toString();

        // 获取请求方法，请求头和请求体
        HttpMethod method = requestEntity.getMethod();
        if (method == null) {
            method = HttpMethod.GET;
        }
        HttpHeaders headers = requestEntity.getHeaders();
        String body = requestEntity.getBody();

        // 获取请求主机和URI
        URI url = requestEntity.getUrl();
        String host = url.getHost();
        String path = url.getPath() + "?" + url.getQuery();

        // 将此次请求存到数据库
        RequestRecord requestRecord = new RequestRecord(
                null, uuid, method.name(), remoteAddr, host, path,
                null,
                0L, Boolean.TRUE, null, null
        );
        requestRecordMapper.insert(requestRecord);
        requestRecord = requestRecordMapper.select(uuid);

        // 查看能否和保护站点匹配
        List<Site> siteList = siteService.findSiteList();
        for (Site site : siteList) {
            // 匹配则进行串并行调度
            String domain = site.getDomain();
            if ("*".equals(domain) || host.equals(domain)) {
                Long requestId = requestRecord.getId();
                if (site.getMode() == 1) {
                    pass = serialForward(requestId, path, method, headers, body);
                    requestRecord.setMode(1);
                } else if (site.getMode() == 0) {
                    pass = parallelForward(requestId, path, method, headers, body);
                    requestRecord.setMode(0);
                }

                if (pass) {
                    // 处理完后，将请求转发到目的地址，并将结果返回
                    String to = "http://" + site.getServer() + path;
                    response = restTemplate.exchange(to, method, new HttpEntity<>(body, headers), String.class);
                } else {
                    response = new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }
            }
        }

        // 更新数据库中的请求时间、请求状态和调度模式
        long time = System.currentTimeMillis() - startTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        requestRecord.setTime(time);
        requestRecord.setPass(pass);
        requestRecordMapper.update(requestRecord);

        // 不匹配返回网关错误
        if (response == null) {
            response = new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }

        return response;
    }

    /**
     * 串行转发
     * @param requestId 请求id
     * @param path 请求路径
     * @param method 请求方法
     * @param headers 请求头
     * @param body 请求体
     * @return 是否放行
     */
    private boolean serialForward(Long requestId, String path, HttpMethod method, HttpHeaders headers, String body) {
        // 获取此次请求选择的waf
        List<Waf> selectWafList = selectWafList();

        boolean pass = true;

        // 依次将请求转发到选择的waf
        ResponseEntity<String> response;
        for (Waf selectWaf : selectWafList) {
            long startTimePerWaf = System.currentTimeMillis();
            String to = "http://" + selectWaf.getIp() + ":" + selectWaf.getPort() + path;
            response = restTemplate.exchange(
                    to, method,
                    new HttpEntity<>(body, headers),
                    String.class
            );
            long time = System.currentTimeMillis() - startTimePerWaf;

            // 请求是否放行
            pass = response.getStatusCode().value() == 200;

            //将此次waf处理的记录存储到数据库
            ScheduleRecord record = new ScheduleRecord(null, time, pass, selectWaf.getId(), requestId, null, null);
            scheduleRecordMapper.insert(record);

            if (!pass) {
                break;
            }
        }

        return pass;
    }


    /**
     * 串行转发
     * @param requestId 请求id
     * @param path 请求路径
     * @param method 请求方法
     * @param headers 请求头
     * @param body 请求体
     * @return 是否放行
     */
    private boolean parallelForward(Long requestId, String path, HttpMethod method, HttpHeaders headers, String body) {

        // 获取此次请求选择的waf
        List<Waf> selectWafList = selectWafList();

        boolean pass = true;

        // 将请求并行转发到选择的waf
        // 创建任务列表
        List<Callable<Boolean>> tasks = new ArrayList<>();
        for (Waf selectWaf : selectWafList) {
            tasks.add(() -> {
                long startTimePerWaf = System.currentTimeMillis();

                String to = "http://" + selectWaf.getIp() + ":" + selectWaf.getPort() + path;
                ResponseEntity<String> response = restTemplate.exchange(to, method, new HttpEntity<>(body, headers), String.class);

                long time = System.currentTimeMillis() - startTimePerWaf;

                // 请求是否放行
                boolean passPerWaf = response.getStatusCode().value() == 200;

                //将此次waf处理的记录存储到数据库
                ScheduleRecord record = new ScheduleRecord(null, time, passPerWaf, selectWaf.getId(), requestId, null, null);
                scheduleRecordMapper.insert(record);

                return passPerWaf;
            });
        }

        // 执行结果并等待指定时间
        List<Future<Boolean>> futures = new ArrayList<>();
        for (Callable<Boolean> task : tasks) {
            futures.add(taskExecutor.submit(task));
        }

        // 处理任务结果
        for (Future<Boolean> future : futures) {
            try{
                pass &= future.get();
            }catch (Exception e){
                log.error("并发请求异常：{}",e.getMessage());
            }

        }
        return pass;

    }

    /**
     * 从waf池中获取waf
     *
     * @return waf列表
     */
    private List<Waf> selectWafList() {

        // 获取waf池
        List<Waf> unSelectWafList = wafMapper.selectListForOnline(true);
        // waf池小于阈值：抛出错误
        Integer wafThreshold = scheduler.getThreshold();
        if (unSelectWafList.size() < wafThreshold) {
            throw new SystemException("waf数量不足");
        }

        // 按照权重降序排序
        unSelectWafList.sort(Comparator.comparingInt(Waf::getWeight).reversed());
        return unSelectWafList.subList(0, scheduler.getThreshold());
    }
}
