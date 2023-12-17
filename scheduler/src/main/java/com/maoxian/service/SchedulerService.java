package com.maoxian.service;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;

public interface SchedulerService {
    /**
     * 串行转发
     */
    ResponseEntity<String> serialForward(HttpServletRequest request, RequestEntity<String> requestEntity);

    /**
     * 并行转发
     */
    ResponseEntity<String> parallelForward(HttpServletRequest request, RequestEntity<String> requestEntity) throws InterruptedException, ExecutionException;
}
