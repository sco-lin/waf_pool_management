package com.maoxian.controller;

import com.maoxian.request.LoginRequest;
import com.maoxian.vo.JsonResult;
import com.maoxian.service.LoginService;
import com.maoxian.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    public JsonResult login(@RequestBody LoginRequest loginRequest) {
        LoginVo loginVo = loginService.login(loginRequest);
        return JsonResult.success(loginVo);
    }

    @GetMapping("logout")
    public JsonResult logout() {
        loginService.logout();
        return JsonResult.success();
    }
}
