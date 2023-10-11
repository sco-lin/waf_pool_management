package com.maoxian.controller;

import com.maoxian.request.LoginRequest;
import com.maoxian.vo.JsonResult;
import com.maoxian.pojo.User;
import com.maoxian.service.LoginService;
import com.maoxian.vo.LoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    public JsonResult login(@RequestBody LoginRequest loginRequest) {
        LoginResult loginResult = loginService.login(loginRequest);
        return JsonResult.success(loginResult);
    }

    @GetMapping("logout")
    public JsonResult logout() {
        loginService.logout();
        return JsonResult.success();
    }
}
