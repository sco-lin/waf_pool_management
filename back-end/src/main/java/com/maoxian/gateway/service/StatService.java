package com.maoxian.gateway.service;

import com.maoxian.gateway.dto.RequestStatDTO;

/**
 * @author Lin
 * @date 2023/11/23 14:30
 */
public interface StatService {

    /**
     * 计算一分钟之内所有请求的平均请求时间
     *
     * @return 平均请求时间
     */
    Integer findAvgRequestTimePerMinute();

    /**
     * 获取恶意请求的情况
     *
     * @return 请求情况
     */
    RequestStatDTO getRequestStatus();
}
