package com.maoxian.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    /**
     * 通过用户id查询权限
     *
     * @param userId 用户id
     * @return 用户权限
     */
    List<String> selectPermsByUserId(Long userId);
}
