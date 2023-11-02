package com.maoxian.service;

import com.maoxian.vo.QueryVo;
import com.maoxian.pojo.User;
import com.maoxian.vo.UserInfoVo;

import java.util.List;

public interface UserService {

    /**
     * 查询用户
     *
     * @param pageNum  第几页
     * @param pageSize 页面大小
     * @param search   模糊查询字符串
     * @return 用户信息
     */
    QueryVo<UserInfoVo> queryUser(Integer pageNum, Integer pageSize, String search);

    /**
     * 查询权限
     *
     * @return 权限信息
     */
    List<String> queryPermByUserId(Integer userId);

    /**
     * 添加或更新用户
     *
     * @param user 用户信息
     */
    void saveOrUpdateUser(User user);

    /**
     * 删除用户
     *
     * @param id id
     */
    void deleteUser(Integer id);

    /**
     * 查询当前用户信息
     *
     * @return 用户信息
     */
    UserInfoVo userInfo(Integer userId);

    /**
     * 设置用户的角色
     *
     * @param userId 用户id
     */
    void setRoleByUserId(Integer userId);
}
