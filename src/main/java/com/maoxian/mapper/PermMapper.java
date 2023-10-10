package com.maoxian.mapper;

import com.maoxian.pojo.Perm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermMapper {
    /**
     * 通过用户id查询权限
     *
     * @param userId 用户id
     * @return 用户权限
     */
    List<String> queryPermByUserId(Long userId);

    /**
     * 增加权限
     *
     * @param perm 权限
     */
    int addPerm(Perm perm);

    /**
     * 更新权限
     *
     * @param perm 权限
     */
    int updatePerm(Perm perm);

    /**
     * 删除权限
     *
     * @param id id
     */
    int deletePerm(Long id);
}
