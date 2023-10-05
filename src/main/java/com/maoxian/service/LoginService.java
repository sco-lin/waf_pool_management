package com.maoxian.service;

import com.maoxian.vo.JsonResult;
import com.maoxian.pojo.User;


public interface LoginService {

    /**
     * 用户登录
     *
     * @param user 用户信息
     * @return 返回token
     */
    JsonResult login(User user);

    /**
     * 用户登出
     *
     * @return 返回登出结果
     */
    JsonResult logout();
}
