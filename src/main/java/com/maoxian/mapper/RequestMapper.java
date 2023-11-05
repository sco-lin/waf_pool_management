package com.maoxian.mapper;

import com.maoxian.pojo.Request;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RequestMapper {

    /**
     * 查询记录数：模糊查询
     *
     * @param uuid 模糊查询字段
     * @return 记录数
     */
    Integer count(String uuid);

    /**
     * 查询请求列表：分页+模糊查询
     *
     * @param start   开始位置
     * @param pageSize 查询个数
     * @param uuid    模糊查询字段
     * @return 请求列表
     */
    List<Request> selectList(int start, int pageSize, String uuid);
}
