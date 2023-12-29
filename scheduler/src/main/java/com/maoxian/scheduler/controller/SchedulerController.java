package com.maoxian.scheduler.controller;

import com.maoxian.scheduler.pojo.Scheduler;
import com.maoxian.scheduler.service.SchedulerService;
import com.maoxian.scheduler.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;

/**
 * @author Lin
 * @date 2023/12/17 23:03
 */
@Controller
public class SchedulerController {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private SchedulerService schedulerService;

    @Autowired
    private SiteService siteService;

    @RequestMapping("/**")
    public ResponseEntity<String> schedule(HttpServletRequest request, RequestEntity<String> requestEntity) throws ExecutionException, InterruptedException {
        String remoteAddr = request.getRemoteAddr();
        return schedulerService.requestHandler(remoteAddr, requestEntity);
    }
}
