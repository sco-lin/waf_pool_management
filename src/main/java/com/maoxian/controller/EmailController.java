package com.maoxian.controller;

import com.maoxian.exceprion.BusinessExp;
import com.maoxian.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("sendCode/{email}")
    public void sendMail(@PathVariable String email) {
        if (email == null) {
            throw new BusinessExp("邮箱不能为空");
        }
        emailService.sendEmailCode(email);
    }

    @GetMapping("sendActivateUrl/{email}")
    public void sendEmailActivateUrl(@PathVariable String email) {
        if (email == null) {
            throw new BusinessExp("邮箱不能为空");
        }
        emailService.sendEmailActivateUrl(email);
    }

    @GetMapping("activate/{email}/{key}")
    public void activate(@PathVariable String email, @PathVariable String key) {
        if (email == null || key == null) {
            throw new BusinessExp("邮箱或key不能为空");
        }
        emailService.activate(email, key);
    }
}
