package com.maoxian.backend.controller;

import com.maoxian.backend.dto.LoginDTO;
import com.maoxian.backend.dto.LoginInfoDTO;
import com.maoxian.backend.exceprion.RequestException;
import com.maoxian.backend.service.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lin
 * @date 2023/9/21 15:30
 */
@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 登录
     *
     * @param loginDTO 登录信息
     * @return 返回token和用户信息
     */
    @PostMapping("/login")
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
    @GetMapping("/logout")
    public void logout() {
        loginService.logout();
    }
}
