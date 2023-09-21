package com.maoxian.service;

import com.maoxian.pojo.ResponseResult;
import com.maoxian.pojo.User;


public interface LoginService {
    ResponseResult login(User user);

    ResponseResult signUp(User user);

    ResponseResult logout();
}
