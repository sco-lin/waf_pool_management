package com.maoxian.gateway.controller;

import com.maoxian.gateway.dto.RequestStatusDTO;
import com.maoxian.gateway.dto.WafIpDTO;
import com.maoxian.gateway.dto.WafPoolDTO;
import com.maoxian.gateway.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Lin
 * @date 2023/11/23 14:30
 */
@RestController
@RequestMapping("stat")
public class StatController {

    @Autowired
    private StatService statService;

    /**
     * 获取waf的上线情况
     *
     * @return waf池状态
     */
    @GetMapping("waf-number")
    public WafPoolDTO getWafPoolStatus() {
        return statService.findWafPoolStatus();
    }

    /**
     * 计算一分钟之内所有请求的平均请求时间
     *
     * @return 平均请求时间
     */
    @GetMapping("time")
    public Integer getAvgRequestTimePerMinute() {
        return statService.findAvgRequestTimePerMinute();
    }

    /**
     * 获取请求成功失败的情况
     *
     * @return 请求情况
     */
    @GetMapping("traffic")
    public RequestStatusDTO getRequestStatus() {
        return statService.getRequestStatus();
    }

    /**
     * 获取waf的IP
     *
     * @return waf信息
     */
    @GetMapping("init")
    public List<WafIpDTO> getWafIp(){
        return statService.getWafIp();
    }
}
