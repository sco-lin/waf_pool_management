package com.maoxian.gateway.service.impl;

import com.maoxian.gateway.exceprion.BusinessException;
import com.maoxian.gateway.mapper.UserMapper;
import com.maoxian.gateway.pojo.User;
import com.maoxian.gateway.service.EmailService;
import com.maoxian.gateway.utils.EmailUtil;
import com.maoxian.gateway.utils.JwtUtil;
import com.maoxian.gateway.utils.RandomUtil;
import com.maoxian.gateway.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Lin
 * @date 2023/10/25 23:27
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private UserMapper userMapper;

    // 有效时长
    @Value("${spring.mail.valid}")
    private Integer valid;

    @Override
    public void sendEmailCode(String targetEmail) {
        // 生成6位随机数字验证码
        String verifyCode = RandomUtil.randomNumbers(6);

        // 发送邮件
        HashMap<String, Object> variables = new HashMap<>();
        variables.put("verifyCode", verifyCode);
        variables.put("valid", valid);
        emailUtil.sendHtmlEmail(targetEmail, "邮箱验证码", "verifyCode.html", variables);

        // 将验证码存入redis，email作为key
        redisCache.setCacheObject("verifyCode:" + targetEmail, verifyCode, valid, TimeUnit.MINUTES);
    }

    @Override
    public void sendEmailActivateUrl(String targetEmail) {
        // 生成jwt作为激活邮件的key
        String jwt = JwtUtil.createJWT(targetEmail);

        // 发送邮件
        HashMap<String, Object> variables = new HashMap<>();
        variables.put("email", targetEmail);
        variables.put("key", jwt);
        emailUtil.sendHtmlEmail(targetEmail, "激活邮件", "activateUrl.html", variables);

        //将激活生成的jwt存入redis
        redisCache.setCacheObject("activate:" + targetEmail, jwt, valid, TimeUnit.MINUTES);
    }

    @Override
    public void activate(String email, String key) {
        // 通过email查询用户
        User user = userMapper.selectByEmail(email);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        if (user.getStatus().equals("0")) {
            throw new BusinessException("账户为已激活状态");
        }

        // 从redis中获取激活邮件的key
        String jwt = redisCache.getCacheObject("activate:" + email);
        if (!key.equals(jwt)) {
            throw new BusinessException("密钥错误");
        }

        // 设置用户的status=0，为激活状态
        user.setStatus("0");
        int count = userMapper.update(user);
        if (count < 1) {
            throw new BusinessException("激活失败");
        }
    }
}
