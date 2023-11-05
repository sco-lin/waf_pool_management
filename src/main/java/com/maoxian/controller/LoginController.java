package com.maoxian.controller;

import com.maoxian.exceprion.BusinessExp;
import com.maoxian.dto.LoginDTO;
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
     * @param loginDTO 登录信息
     * @return 返回token和用户信息
     */
    @PostMapping("login")
    public LoginVo login(@RequestBody LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        String verifyCode = loginDTO.getVerifyCode();

        if (username.isEmpty()) {
            throw new BusinessExp("用户名不能为空");
        }
        if (password.isEmpty()) {
            throw new BusinessExp("密码不能为空");
        }
        if (verifyCode.isEmpty()) {
            throw new BusinessExp("验证码不能为空");
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
