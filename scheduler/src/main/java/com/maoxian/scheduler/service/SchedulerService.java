package com.maoxian.scheduler.service;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

/**
 * @author Lin
 * @date 2023/12/17 23:03
 */
public interface SchedulerService {

    /**
     * 请求处理
     *
     * @param remoteAddr    客户端地址
     * @param requestEntity 请求报文
     * @return 响应报文
     */
    ResponseEntity<String> requestHandler(String remoteAddr, RequestEntity<String> requestEntity);
}
