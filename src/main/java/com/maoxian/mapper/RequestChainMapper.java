package com.maoxian.mapper;

import com.maoxian.pojo.RequestChain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RequestChainMapper {

    /**
     * 通过parentId查询请求链列表
     *
     * @param requestId 夫id
     * @return 请求链列表
     */
    List<RequestChain> selectList(Integer requestId);
}
