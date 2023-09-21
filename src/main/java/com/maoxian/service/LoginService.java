package com.maoxian.service;

import com.maoxian.pojo.ResponseResult;
import com.maoxian.pojo.User;

import java.util.Map;


public interface LoginService {

    /**
     * 用户登录
     *
     * @param user 用户信息
     * @return 返回token
     */
    ResponseResult<Map> login(User user);

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 返回注册结果
     */
    ResponseResult<Object> signUp(User user);

    /**
     * 用户登出
     *
     * @return 返回登出结果
     */
    ResponseResult<Object> logout();
}
