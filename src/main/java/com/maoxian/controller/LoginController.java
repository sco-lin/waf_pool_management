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

    @PostMapping("login")
    public LoginVo login(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }

    @GetMapping("logout")
    public void logout() {
        loginService.logout();
    }
}
