package com.maoxian.backend.service;

import com.maoxian.backend.dto.UserBaseInfoDTO;
import com.maoxian.backend.dto.UserInfoDTO;
import com.maoxian.backend.dto.UserPasswordDTO;
import com.maoxian.backend.pojo.User;

import java.util.List;

/**
 * @author Lin
 * @date 2023/10/5 13:28
 */
public interface UserService {

    /**
     * 查询用户
     *
     * @return 用户信息
     */
    List<UserInfoDTO> findUserInfoList();

    /**
     * 增加或修改用户
     *
     * @param user 用户信息
     */
    void addOrModifyUser(User user);

    /**
     * 修改用户的基本信息
     *
     * @param userBaseInfoDTO 用户基本信息
     */
    void modifyUserBaseInfo(UserBaseInfoDTO userBaseInfoDTO);

    /**
     * 修改密码
     *
     * @param userPasswordDTO 用户密码
     */
    void modifyPassword(UserPasswordDTO userPasswordDTO);

    /**
     * 通过id删除用户
     *
     * @param id 删除条件
     */
    void deleteUserById(Long id);
}
