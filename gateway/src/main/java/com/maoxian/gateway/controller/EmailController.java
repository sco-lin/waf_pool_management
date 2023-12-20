package com.maoxian.gateway.controller;

import com.maoxian.gateway.exceprion.RequestException;
import com.maoxian.gateway.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lin
 * @date 2023/10/25 23:27
 */
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
            throw new RequestException("邮箱不能为空");
        }
        emailService.sendEmailCode(email);
    }

    //TODO 预计废除
    @GetMapping("sendActivateUrl/{email}")
    public void sendEmailActivateUrl(@PathVariable String email) {
        if (email.isEmpty()) {
            throw new RequestException("邮箱不能为空");
        }
        emailService.sendEmailActivateUrl(email);
    }

    //TODO 预计废除
    @GetMapping("activate/{email}/{key}")
    public void activate(@PathVariable String email, @PathVariable String key) {
        if (email.isEmpty() || key.isEmpty()) {
            throw new RequestException("邮箱或key不能为空");
        }
        emailService.activate(email, key);
    }
}
