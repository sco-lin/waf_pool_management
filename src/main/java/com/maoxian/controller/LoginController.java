package com.maoxian.controller;

import com.maoxian.vo.JsonResult;
import com.maoxian.pojo.User;
import com.maoxian.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    public JsonResult login(@RequestBody User user) {
        return loginService.login(user);
    }

    @GetMapping("logout")
    public JsonResult logout() {
        return loginService.logout();
    }
}
