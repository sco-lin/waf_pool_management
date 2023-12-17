package com.maoxian.controller;

import com.maoxian.config.SchedulerConfig;
import com.maoxian.exception.SystemExp;
import com.maoxian.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;

@Controller
public class SchedulerController {

    @Autowired
    private SchedulerConfig schedulerConfig;

    @Autowired
    private SchedulerService schedulerService;

    @RequestMapping
    public ResponseEntity<String> schedule(HttpServletRequest request, RequestEntity<String> requestEntity) throws ExecutionException, InterruptedException {
        ResponseEntity<String> response;
        // 根据调度器的调度模式，进行不同的处理
        if (schedulerConfig.getSchedulerMode() == 0) {
            response = schedulerService.serialForward(request, requestEntity);
        } else if (schedulerConfig.getSchedulerMode() == 1) {
            response = schedulerService.parallelForward(request, requestEntity);
        } else {
            throw new SystemExp("schedule.mode配置的参数无效");
        }
        return response;
    }
}
