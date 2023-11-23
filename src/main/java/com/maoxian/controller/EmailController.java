package com.maoxian.controller;

import com.maoxian.exceprion.BusinessExp;
import com.maoxian.exceprion.RequestExp;
import com.maoxian.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    /**
     * 发送邮箱验证码
     *
     * @param email 邮箱
     */
    @GetMapping("sendCode/{email}")
    public void sendMail(@PathVariable String email) {
        if (email.isEmpty()) {
            throw new RequestExp("邮箱不能为空");
        }
        emailService.sendEmailCode(email);
    }

    //TODO 预计废除
    @GetMapping("sendActivateUrl/{email}")
    public void sendEmailActivateUrl(@PathVariable String email) {
        if (email.isEmpty()) {
            throw new RequestExp("邮箱不能为空");
        }
        emailService.sendEmailActivateUrl(email);
    }

    //TODO 预计废除
    @GetMapping("activate/{email}/{key}")
    public void activate(@PathVariable String email, @PathVariable String key) {
        if (email.isEmpty() || key.isEmpty()) {
            throw new RequestExp("邮箱或key不能为空");
        }
        emailService.activate(email, key);
    }
}
