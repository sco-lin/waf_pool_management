package com.maoxian.service;

import com.maoxian.dto.UserPasswordDTO;
import com.maoxian.dto.UserBaseInfoDTO;
import com.maoxian.dto.UserInfoDTO;
import com.maoxian.dto.PageResult;
import com.maoxian.pojo.User;

public interface UserService {

    /**
     * 查询用户
     *
     * @param pageNum  第几页
     * @param pageSize 页面大小
     * @param search   模糊查询字符串
     * @return 用户信息
     */
    PageResult<UserInfoDTO> findUserInfo(Integer pageNum, Integer pageSize, String search);

    /**
     * 增加用户
     *
     * @param user 用户信息
     */
    void addUser(User user);

    /**
     * 修改用户基本信息
     *
     * @param userBaseInfoDTO 用户基本信息
     */
    void modifyUser(UserBaseInfoDTO userBaseInfoDTO);

    /**
     * 修改用户密码
     *
     * @param userPasswordDTO 用户密码
     */
    void modifyUser(UserPasswordDTO userPasswordDTO);

    /**
     * 通过id删除用户
     *
     * @param id 删除条件
     */
    void deleteUserById(Integer id);

    /**
     * 通过id查询用户信息
     *
     * @param id 查询条件
     * @return 用户信息
     */
    UserInfoDTO findUserInfoById(Integer id);

}
