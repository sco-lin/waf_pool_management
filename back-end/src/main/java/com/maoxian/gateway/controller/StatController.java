package com.maoxian.gateway.controller;

import com.maoxian.gateway.dto.RequestStatDTO;
import com.maoxian.gateway.service.StatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lin
 * @date 2023/11/23 14:30
 */
@RestController
@RequestMapping("/stat")
public class StatController {

    private final StatService statService;

    public StatController(StatService statService) {
        this.statService = statService;
    }

    /**
     * 计算一分钟之内所有请求的平均请求时间
     *
     * @return 平均请求时间
     */
    @GetMapping("/time")
    public Integer getAvgRequestTimePerMinute() {
        return statService.findAvgRequestTimePerMinute();
    }

    /**
     * 获取恶意请求的情况
     *
     * @return 请求情况
     */
    @GetMapping("/traffic")
    public RequestStatDTO getRequestStatus() {
        return statService.getRequestStatus();
    }
}
