package com.maoxian.gateway.service;

import com.maoxian.gateway.dto.PageResult;
import com.maoxian.gateway.dto.RequestRecordDTO;
import com.maoxian.gateway.dto.ScheduleRecordDTO;

/**
 * @author Lin
 * @date 2023/11/5 9:14
 */
public interface RequestService {

    /**
     * 查询所有请求：分页+模糊查询
     *
     * @param pageNum 第几页
     * @param paeSize 页面大小
     * @param uuid    模糊查询字段
     * @return 查询结果
     */
    PageResult<RequestRecordDTO> findRequest(Integer pageNum, Integer paeSize, String uuid);

    /**
     * 查询请求的调度记录
     *
     * @param requestId 请求id
     * @return 调度记录
     */
    ScheduleRecordDTO findScheduleRecord(Long requestId);
}
