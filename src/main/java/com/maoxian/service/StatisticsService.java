package com.maoxian.service;

import com.maoxian.dto.RequestStatusDTO;
import com.maoxian.dto.WafPoolDTO;

public interface StatisticsService {
    /**
     * 获取waf的上线情况
     *
     * @return waf池状态
     */
    WafPoolDTO findWafPoolStatus();

    /**
     * 计算一分钟之内所有请求的平均请求时间
     *
     * @return 平均请求时间
     */
    Integer findAvgRequestTimePerMinute();

    /**
     * 获取请求成功失败的情况
     *
     * @return 请求情况
     */
    RequestStatusDTO getRequestStatus();
}
