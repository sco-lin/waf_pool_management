package com.maoxian.backend.mapper;

import com.maoxian.backend.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Lin
 * @date 2023/10/5 13:28
 */
@Mapper
public interface RoleMapper {

    /**
     * 通过用户id查询角色
     *
     * @param userId 查询条件
     * @return 角色信息
     */
    List<String> selectByUserId(Long userId);

    /**
     * 增加角色
     *
     * @param role 角色
     * @return 更改行数
     */
    int insert(Role role);

    /**
     * 删除角色
     *
     * @param role 角色
     * @return 更改行数
     */
    int update(Role role);

    /**
     * 通过i删除角色
     *
     * @param id 删除条件
     * @return 更改行数
     */
    int deleteById(Long id);
}
