package com.maoxian.controller;

import com.maoxian.pojo.ResponseResult;
import com.maoxian.pojo.User;
import com.maoxian.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("user/login")
    public ResponseResult login(@RequestBody User user) {
        return loginService.login(user);
    }

    @PostMapping("/user/register")
    @PreAuthorize("hasAuthority('admin:register')")
    public ResponseResult signUp(@RequestBody User user) {
        return loginService.signUp(user);
    }

    @GetMapping("user/logout")
    public ResponseResult logout() {
        return loginService.logout();
    }
}
