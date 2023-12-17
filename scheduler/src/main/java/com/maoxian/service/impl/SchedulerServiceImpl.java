package com.maoxian.service.impl;

import com.maoxian.config.SchedulerConfig;
import com.maoxian.exception.SystemExp;
import com.maoxian.mapper.WafMapper;
import com.maoxian.pojo.Waf;
import com.maoxian.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.*;


public class SchedulerServiceImpl implements SchedulerService {

    private static final int NUM_THREADS = 10;
    private static final long TIMEOUT_SECONDS = 3;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WafMapper wafMapper;

    @Autowired
    private SchedulerConfig schedulerConfig;

    private final Map<String, List<Waf>> requestPool = new HashMap<>();

    @Override
    public ResponseEntity<String> serialForward(HttpServletRequest request, RequestEntity<String> requestEntity) {

        HttpEntity<String> entity = null;
        // 获取请求头"X-UUID"，用于跟踪请求
        String requestUuid = requestEntity.getHeaders().getFirst("X-UUID");
        if (requestUuid == null || requestUuid.isEmpty()) {
            // 修改请求头
            entity = modifyHeader(request.getRemoteAddr(), requestEntity);
            /*
             * TODO 可能存在问题
             * 创建一个请求实例Request{int count,List<Waf>}
             * 将实例保存到状态{requestUuid,Request}
             * */
            List<Waf> newRequest = new ArrayList<>();
            requestPool.put(requestUuid, newRequest);
        }

        // 获取转发地址
        List<Waf> wafList = requestPool.get(requestUuid);
        if (wafList == null) {
            throw new SystemExp("请求找不到");
        }
        int wafNum = wafList.size();

        // waf数量==阈值
        URI newUrl = null;
        if (wafNum == schedulerConfig.getWafThreshold()) {
            // 组合目标主机和url为一个完整的URL字符串并解析为字符串格式
            try {
                newUrl = new URI(requestEntity.getHeaders().getFirst("X-Forwarded-Host") + requestEntity.getUrl());
            } catch (URISyntaxException e) {
                throw new RuntimeException("URI解析错误");
            }
        } else if (wafNum > schedulerConfig.getWafThreshold()) {
            // waf数量>阈值：向客户端响应内容
            return new ResponseEntity<>("请求头被非法修改", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            // 从waf列表中请求一个waf
            Waf selectedWaf = selectOneWaf(wafList);
            try {
                newUrl = new URI(selectedWaf.getUrl());
            } catch (URISyntaxException e) {
                throw new RuntimeException("URI解析错误");
            }
            wafList.add(selectedWaf);
        }

        // 设置请求的url，并使用restTemplate发送请求
        HttpMethod method = requestEntity.getMethod();
        if (method == null) {
            method = HttpMethod.GET;
        }
        ResponseEntity<String> response = restTemplate.exchange(newUrl, method, entity, String.class);

        // 对返回进行处理
        requestUuid = response.getHeaders().getFirst("X-UUID");
        List<Waf> wafs = requestPool.get(requestUuid);
        int count = wafList.size();
        if (schedulerConfig.getWafThreshold() == 0 || count == 0) {
            requestPool.remove(requestUuid);
            HttpStatus statusCode = response.getStatusCode();
            // TODO 获取请求头，添加日志到数据库

            if (statusCode.value() != 200) {
                String body = response.getBody();
                // TODO 打印日志到控制台
            }
        }

        return response;
    }


    @Override
    public ResponseEntity<String> parallelForward(HttpServletRequest request, RequestEntity<String> requestEntity) throws InterruptedException, ExecutionException {
        HttpEntity<String> entity = null;
        // 获取请求头"X-UUID"，用于跟踪请求
        String requestUuid = requestEntity.getHeaders().getFirst("X-UUID");
        if (requestUuid == null || requestUuid.isEmpty()) {
            // 修改请求头
            entity = modifyHeader(request.getRemoteAddr(), requestEntity);
        }

        List<Waf> wafs = selectWafs();
        HttpMethod method = requestEntity.getMethod();
        if (method == null) {
            method = HttpMethod.GET;
        }
        boolean flag = true;

        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        List<Callable<ResponseEntity<String>>> tasks = new ArrayList<>();
        for (int i = 1; i <= wafs.size(); i++) {
            int finalIndex = i;
            HttpMethod finalMethod = method;
            HttpEntity<String> finalEntity = entity;
            tasks.add(() -> restTemplate.exchange(wafs.get(finalIndex).getUrl(), finalMethod, finalEntity, String.class));
        }

        List<Future<ResponseEntity<String>>> futures = executorService.invokeAll(tasks, TIMEOUT_SECONDS, TimeUnit.SECONDS);


        for (Future<ResponseEntity<String>> future : futures) {
            if (future.isDone() && !future.isCancelled()) {
                ResponseEntity<String> data = future.get();
                if (data.getStatusCode().value() != 200) {
                    flag = false;
                }
            }

        }
        executorService.shutdown();

        if (flag) {

            // 组合目标主机和url为一个完整的URL字符串并解析为字符串格式
            URI newUrl;
            try {
                newUrl = new URI(requestEntity.getHeaders().getFirst("X-Forwarded-Host") + requestEntity.getUrl());
            } catch (URISyntaxException e) {
                throw new RuntimeException("URI解析错误");
            }

            ResponseEntity<String> response = restTemplate.exchange(newUrl, method, entity, String.class);

            HttpStatus statusCode = response.getStatusCode();
            // TODO 获取请求头，添加日志到数据库

            if (statusCode.value() != 200) {
                String body = response.getBody();
                // TODO 打印日志到控制台
            }
            return response;
        } else {
            return new ResponseEntity<>("请求头被非法修改", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private Waf selectOneWaf(List<Waf> wafList) {
        // 移除重复的waf
        List<Waf> unSelectWafList = removeWafDuplicates(wafList);
        // waf选择模式
        switch (schedulerConfig.getWafMode()) {
            case 1:
                return weighedSelect(unSelectWafList);
            case 0:
            default:
                return randomSelect(unSelectWafList);
        }
    }

    private List<Waf> selectWafs() {
        List<Waf> result = new ArrayList<>();
        // 获取waf池
        List<Waf> unSelectWafList = wafMapper.selectListForStatus(0);
        Random random = new Random();
        for (int i = 0; i < schedulerConfig.getWafThreshold(); i++) {
            int count = random.nextInt(unSelectWafList.size());
            result.add(unSelectWafList.get(i));
            unSelectWafList.remove(i);
        }
        return result;
    }

    // TODO 简单实现
    private Waf weighedSelect(List<Waf> unSelectWafList) {
        Waf result = unSelectWafList.get(0);
        for (Waf waf : unSelectWafList) {
            if (waf.getWeight() > result.getWeight()) {
                result = waf;
            }
        }
        return result;
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
            throw new SystemExp("waf数量不足");
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
