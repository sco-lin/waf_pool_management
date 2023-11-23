package com.maoxian.service.impl;

import com.maoxian.dto.RequestStatusDTO;
import com.maoxian.dto.WafPoolDTO;
import com.maoxian.mapper.RequestMapper;
import com.maoxian.mapper.WafMapper;
import com.maoxian.mapper.WafStatusMapper;
import com.maoxian.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private WafMapper wafMapper;

    @Autowired
    private RequestMapper requestMapper;

    @Override
    public WafPoolDTO findWafPoolStatus() {
        // 获取总waf数和上线的waf数
        Integer total = wafMapper.count();
        Integer online = wafMapper.countForStatus(2);
        return new WafPoolDTO(total, online);
    }

    @Override
    public Integer findAvgRequestTimePerMinute() {
        // 获取当前时间的前一分钟
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime targetTime = now.minusMinutes(1);
        // 查询指定时间后的请求的请求时间，并计算平均时间
        List<Integer> times = requestMapper.selectRequestTimeAfterTargetTime(targetTime);
        if (times == null) {
            throw new RuntimeException("查询请求时间失败");
        }
        if (times.isEmpty()){
            return null;
        }
        int sum = times.stream().mapToInt(Integer::intValue).sum();
        return sum / times.size();
    }

    @Override
    public RequestStatusDTO getRequestStatus() {
        // 获取总请求和失败请求数
        Integer total = requestMapper.count(null);
        Integer malicious = requestMapper.countForStatus(1);
        return new RequestStatusDTO(total,malicious);
    }
}
