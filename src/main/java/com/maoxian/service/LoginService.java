package com.maoxian.service;

import com.maoxian.pojo.User;

import java.util.Map;


public interface LoginService {

    /**
     * 用户登录
     *
     * @param user 用户信息
     * @return map 登录信息
     */
    Map<String, String> login(User user);

    /**
     * 用户登出
     */
    void logout();
}
