package com.maoxian.controller;

import com.maoxian.dto.RequestStatusDTO;
import com.maoxian.dto.WafPoolDTO;
import com.maoxian.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stat")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    /**
     * 获取waf的上线情况
     *
     * @return waf池状态
     */
    @GetMapping("waf-number")
    public WafPoolDTO getWafPoolStatus() {
        return statisticsService.findWafPoolStatus();
    }

    /**
     * 计算一分钟之内所有请求的平均请求时间
     *
     * @return 平均请求时间
     */
    @GetMapping("time")
    public Integer getAvgRequestTimePerMinute() {
        return statisticsService.findAvgRequestTimePerMinute();
    }

    /**
     * 获取请求成功失败的情况
     *
     * @return 请求情况
     */
    @GetMapping("traffic")
    public RequestStatusDTO getRequestStatus() {
        return statisticsService.getRequestStatus();
    }
}
