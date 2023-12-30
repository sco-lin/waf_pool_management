package com.maoxian.backend.service.impl;

import com.maoxian.backend.dto.RequestStatDTO;
import com.maoxian.backend.mapper.RequestRecordMapper;
import com.maoxian.backend.service.StatService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Lin
 * @date 2023/11/23 14:30
 */
@Service
public class StatServiceImpl implements StatService {

    private final RequestRecordMapper requestMapper;

    public StatServiceImpl(RequestRecordMapper requestMapper) {
        this.requestMapper = requestMapper;
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
        if (times.isEmpty()) {
            return null;
        }
        int sum = times.stream().mapToInt(Integer::intValue).sum();
        return sum / times.size();
    }

    @Override
    public RequestStatDTO getRequestStatus() {
        // 获取总请求和失败请求数
        Integer total = requestMapper.count(null);
        Integer malicious = requestMapper.countForStatus(1);
        return new RequestStatDTO(total, malicious);
    }
}
