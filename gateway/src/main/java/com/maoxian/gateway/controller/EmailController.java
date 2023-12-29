package com.maoxian.gateway.controller;

import com.maoxian.gateway.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lin
 * @date 2023/10/25 23:27
 */
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    /**
     * 发送邮箱验证码
     *
     * @param email 邮箱
     */
    @GetMapping("/code/{email}")
    public void sendEmailCode(@PathVariable String email) {
        emailService.sendEmailCode(email);
    }
}
