package com.maoxian.service;

import com.maoxian.pojo.User;
import com.maoxian.request.LoginRequest;
import com.maoxian.vo.LoginResult;

import java.util.Map;


public interface LoginService {

    /**
     * 用户登录
     *
     * @param loginRequest 用户名密码
     * @return map 登录信息
     */
    LoginResult login(LoginRequest loginRequest);

    /**
     * 用户登出
     */
    void logout();
}
