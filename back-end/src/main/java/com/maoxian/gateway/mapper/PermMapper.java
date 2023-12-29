package com.maoxian.gateway.mapper;

import com.maoxian.gateway.pojo.Perm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Lin
 * @date 2023/9/21 15:30
 */
@Mapper
public interface PermMapper {
    /**
     * 通过用户id查询权限
     *
     * @param userId 查询条件
     * @return 用户权限
     */
    List<String> selectByUserId(Long userId);

    /**
     * 增加权限
     *
     * @param perm 权限
     * @return 更改行数
     */
    int insert(Perm perm);

    /**
     * 更新权限
     *
     * @param perm 权限
     * @return 更改行数
     */
    int update(Perm perm);

    /**
     * 通过id删除权限
     *
     * @param id 删除条件
     * @return 更改行数
     */
    int deleteById(Long id);
}
