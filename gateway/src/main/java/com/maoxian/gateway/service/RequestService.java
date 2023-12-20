package com.maoxian.gateway.service;

import com.maoxian.gateway.dto.SchedoleRecordDTO;
import com.maoxian.gateway.pojo.RequestRecord;
import com.maoxian.gateway.dto.PageResult;

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
    PageResult<RequestRecord> findRequest(Integer pageNum, Integer paeSize, String uuid);

    /**
     * 通过请求id查询请求数组
     *
     * @param requestId 请求id
     * @return 请求数组
     */
    SchedoleRecordDTO findRequestDetailList(Integer requestId);
}
