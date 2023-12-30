package com.maoxian.scheduler.controller;

import com.maoxian.scheduler.service.SchedulerService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lin
 * @date 2023/12/17 23:03
 */
@Controller
public class SchedulerController {

    private final SchedulerService schedulerService;

    public SchedulerController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @RequestMapping("/**")
    public ResponseEntity<String> schedule(HttpServletRequest request, RequestEntity<String> requestEntity) {
        String remoteAddr = request.getRemoteAddr();
        return schedulerService.requestHandler(remoteAddr, requestEntity);
    }
}
