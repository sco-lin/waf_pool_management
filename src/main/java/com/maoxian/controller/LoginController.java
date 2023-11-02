package com.maoxian.controller;

import com.maoxian.request.LoginRequest;
import com.maoxian.service.LoginService;
import com.maoxian.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     *
     * @param loginRequest 登录信息
     * @return 返回token和用户信息
     */
    @PostMapping("login")
    public LoginVo login(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }

    /**
     * 注销
     */
    @GetMapping("logout")
    public void logout() {
        loginService.logout();
    }
}
