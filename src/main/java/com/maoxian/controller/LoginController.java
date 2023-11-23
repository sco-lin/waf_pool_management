package com.maoxian.controller;

import com.maoxian.exceprion.BusinessExp;
import com.maoxian.dto.LoginDTO;
import com.maoxian.exceprion.RequestExp;
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

        if (username == null || username.isEmpty()) {
            throw new RequestExp("用户名不能为空");
        }
        if (password == null || password.isEmpty()) {
            throw new RequestExp("密码不能为空");
        }
        if (verifyCode == null || verifyCode.isEmpty()) {
            throw new RequestExp("验证码不能为空");
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
