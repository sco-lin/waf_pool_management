package com.maoxian.service.impl;

import com.maoxian.exceprion.BusinessExp;
import com.maoxian.mapper.UserMapper;
import com.maoxian.pojo.User;
import com.maoxian.service.EmailService;
import com.maoxian.utils.EmailUtil;
import com.maoxian.utils.JwtUtil;
import com.maoxian.utils.RandomUtil;
import com.maoxian.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private UserMapper userMapper;

    // 发送方邮箱
    @Value("${spring.mail.username}")
    private String email;

    // 有效时长
    @Value("${spring.mail.valid}")
    private Integer valid;

    // 内容模板
    @Value("${spring.mail.template}")
    private String template;

    // 标题
    @Value("${spring.mail.title}")
    private String title;

    @Override
    public void sendEmailCode(String targetEmail) {
        // 生成6位随机数字验证码
        String verifyCode = RandomUtil.randomNumbers(6);

        // 发送邮件
        String content = String.format(template, verifyCode, valid);
        emailUtil.sendEmail(targetEmail, title, content);

        // 将验证码存入redis，email作为key
        redisCache.setCacheObject("verifyCode:" + targetEmail, verifyCode);
    }

    @Override
    public void sendEmailActivateUrl(String targetEmail) {
        // 生成jwt
        String jwt = JwtUtil.createJWT(targetEmail);

        String serverUrl = "http://localhost:8080";

        String content = String.format("点击此链接进行激活：<a href='%s/email/activate/%s/%s'>点我激活</a>", serverUrl, targetEmail, jwt);
        emailUtil.sendEmail(targetEmail, "激活链接", content);

        //将激活生成的jwt存入redis
        redisCache.setCacheObject("activate:" + targetEmail, jwt);
    }

    @Override
    public void activate(String email, String key) {
        User user = userMapper.queryUserByEmail(email);
        if (user == null) {
            throw new BusinessExp("用户不存在");
        }
        if (user.getStatus().equals("0")) {
            throw new BusinessExp("账户为已激活状态");
        }

        String jwt = redisCache.getCacheObject("activate:" + email);
        if (!key.equals(jwt)) {
            throw new BusinessExp("密钥失败");
        }

        user.setStatus("0");
        int count = userMapper.updateUser(user);
        if (count < 1) {
            throw new BusinessExp("激活失败");
        }
    }
}
