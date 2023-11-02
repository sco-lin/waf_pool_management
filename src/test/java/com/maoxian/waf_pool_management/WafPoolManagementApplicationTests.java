package com.maoxian.waf_pool_management;

import com.maoxian.mapper.PermMapper;
import com.maoxian.mapper.LogMapper;
import com.maoxian.mapper.UserMapper;
import com.maoxian.pojo.User;
import com.maoxian.service.LoginService;

import com.maoxian.service.UserService;
import com.maoxian.service.WafService;
import com.maoxian.utils.EmailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class WafPoolManagementApplicationTests {

    @Autowired
    LogMapper logMapper;

    @Autowired LoginService loginService;

    @Autowired
    private PermMapper permMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private WafService wafService;

    private String emailRegular = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$";

    private String ipRegular = "^(((\\d)|([1-9]\\d)|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d)|([1-9]\\d)|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))$";

    @Test
    public void testRegular(){
        System.out.println("zls@163.com".matches(emailRegular));
        System.out.println("sdff@af".matches(emailRegular));
        System.out.println("12.54.18.24".matches(ipRegular));
        System.out.println("15.19.168.256".matches(ipRegular));
    }

    @Test
    public void testSelectPermsByUserId(){
        List<String> list = permMapper.queryPermByUserId(1);
        System.out.println(list);
    }
    @Test
    public void testUser(){
        User user = userMapper.queryUserById(1);
        System.out.println(user);
    }

    @Test
    public void testSendTemplateEmail(){
        Map<String, Object> variables = new HashMap<>();
        variables.put("email","zls2434474199@163.com");
        variables.put("key","dddddd");
//        variables.put("verifyCode","12345");
//        variables.put("valid",5);
        emailUtil.sendHtmlEmail("zls2434474199@163.com","测试","activateUrl.html",variables);
    }

}
