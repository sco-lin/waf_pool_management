package com.maoxian.service;

import com.maoxian.request.LoginRequest;
import com.maoxian.vo.LoginVo;

public interface LoginService {

    /**
     * 用户登录
     *
     * @param loginRequest 用户名密码
     * @return map 登录信息
     */
    LoginVo login(LoginRequest loginRequest);

    /**
     * 用户登出
     */
    void logout();
}
