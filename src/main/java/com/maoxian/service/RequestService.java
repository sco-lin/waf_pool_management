package com.maoxian.service;

import com.maoxian.dto.RequestChainDTO;
import com.maoxian.dto.RequestDTO;
import com.maoxian.vo.PageResult;

import java.util.List;

public interface RequestService {

    /**
     * 查询所有请求：分页+模糊查询
     *
     * @param pageNum 第几页
     * @param paeSize 页面大小
     * @param uuid    模糊查询字段
     * @return 查询结果
     */
    PageResult<RequestDTO> findRequest(Integer pageNum, Integer paeSize, String uuid);

    /**
     * 通过请求id查询请求链
     *
     * @param requestId 请求id
     * @return 请求链
     */
    List<RequestChainDTO> findRequestChainList(Integer requestId);
}
