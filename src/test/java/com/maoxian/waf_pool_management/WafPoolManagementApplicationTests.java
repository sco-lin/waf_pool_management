package com.maoxian.waf_pool_management;

import com.maoxian.mapper.PermMapper;
import com.maoxian.mapper.UserMapper;
import com.maoxian.pojo.User;
import com.maoxian.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WafPoolManagementApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired LoginService loginService;


    @Test
    void contextLoads() {
        List<User> user = userMapper.queryUserList(1,2);
        System.out.println(user);

    }

    @Autowired
    private PermMapper permMapper;

    @Test
    public void testSelectPermsByUserId(){
        List<String> list = permMapper.queryPermByUserId(1);
        System.out.println(list);
    }

}
