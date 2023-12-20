package com.maoxian.gateway.service;

import com.maoxian.gateway.dto.LoginInfoDTO;

/**
 * @author Lin
 * @date 2023/9/21 15:30
 */
public interface LoginService {

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @param verifyCode 验证码
     * @return 登录信息
     */
    LoginInfoDTO login(String username, String password, String verifyCode);

    /**
     * 用户登出
     */
    void logout();
}
