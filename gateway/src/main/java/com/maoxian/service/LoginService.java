package com.maoxian.service;

import com.maoxian.dto.LoginInfoDTO;

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
