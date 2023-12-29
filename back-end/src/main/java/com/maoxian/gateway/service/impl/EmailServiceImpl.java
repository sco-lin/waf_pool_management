package com.maoxian.gateway.service.impl;

import com.maoxian.gateway.service.EmailService;
import com.maoxian.gateway.util.EmailUtil;
import com.maoxian.gateway.util.RandomUtil;
import com.maoxian.gateway.util.RedisCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Lin
 * @date 2023/10/25 23:27
 */
@Service
public class EmailServiceImpl implements EmailService {

    private final EmailUtil emailUtil;

    private final RedisCache redisCache;

    public EmailServiceImpl(EmailUtil emailUtil, RedisCache redisCache) {
        this.emailUtil = emailUtil;
        this.redisCache = redisCache;
    }

    @Override
    public void sendEmailCode(String email) {
        // 生成6位随机数字验证码
        String verifyCode = RandomUtil.randomNumbers(6);

        // 发送邮件
        emailUtil.sendHtmlEmail(email, "waf池管理系统", "你此次的登录验证码为:" + verifyCode + ",有效期5分钟");

        // 将验证码存入redis，email作为key
        redisCache.setCacheObject("verifyCode:" + email, verifyCode, 5, TimeUnit.MINUTES);
    }
}
