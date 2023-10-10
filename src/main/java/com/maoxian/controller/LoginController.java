package com.maoxian.controller;

import com.maoxian.vo.JsonResult;
import com.maoxian.pojo.User;
import com.maoxian.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    public JsonResult login(@RequestBody User user) {
        Map<String, String> map = loginService.login(user);
        return JsonResult.success(map);
    }

    @GetMapping("logout")
    public JsonResult logout() {
        loginService.logout();
        return JsonResult.success();
    }
}
