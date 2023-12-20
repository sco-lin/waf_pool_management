package com.maoxian.controller;

import com.maoxian.dto.LoginDTO;
import com.maoxian.exceprion.RequestException;
import com.maoxian.service.LoginService;
import com.maoxian.dto.LoginInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lin
 * @date 2023/9/21 15:30
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     *
     * @param loginDTO 登录信息
     * @return 返回token和用户信息
     */
    @PostMapping("login")
    public LoginInfoDTO login(@RequestBody LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        String verifyCode = loginDTO.getVerifyCode();

        if (username == null || username.isEmpty()) {
            throw new RequestException("用户名不能为空");
        }
        if (password == null || password.isEmpty()) {
            throw new RequestException("密码不能为空");
        }
        if (verifyCode == null || verifyCode.isEmpty()) {
            throw new RequestException("验证码不能为空");
        }
        return loginService.login(username, password, verifyCode);
    }

    /**
     * 注销
     */
    @GetMapping("logout")
    public void logout() {
        loginService.logout();
    }
}
