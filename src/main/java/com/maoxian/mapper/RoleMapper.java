package com.maoxian.mapper;

import com.maoxian.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    /**
     * 通过用户id查询角色
     *
     * @param userId 用户id
     * @return 角色信息
     */
    List<String> queryRoleByUserId(Long userId);

    /**
     * 增加角色
     *
     * @param role 角色
     */
    void addRole(Role role);

    /**
     * 删除角色
     *
     * @param role 角色
     */
    void updateRole(Role role);

    /**
     * 删除角色
     *
     * @param id id
     */
    void deleteRole(Long id);
}
