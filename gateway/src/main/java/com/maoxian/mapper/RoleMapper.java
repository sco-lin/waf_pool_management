package com.maoxian.mapper;

import com.maoxian.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    /**
     * 通过用户id查询角色
     *
     * @param userId 查询条件
     * @return 角色信息
     */
    List<String> selectByUserId(Integer userId);

    /**
     * 增加角色
     *
     * @param role 角色
     */
    int insert(Role role);

    /**
     * 删除角色
     *
     * @param role 角色
     */
    int update(Role role);

    /**
     * 通过i删除角色
     *
     * @param id 删除条件
     */
    int deleteById(Integer id);
}
