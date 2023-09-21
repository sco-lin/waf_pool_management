package com.maoxian.waf_pool_management;

import com.maoxian.mapper.MenuMapper;
import com.maoxian.mapper.UserMapper;
import com.maoxian.pojo.User;
import com.maoxian.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class WafPoolManagementApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired LoginService loginService;


    @Test
    void contextLoads() throws SQLException {
        System.out.println("===================");
        User user = userMapper.queryUserByUsername("zhangsan");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean matches = bCryptPasswordEncoder.matches("123456", user.getPassword());
        System.out.println(user);
        System.out.println(matches);

    }

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void testSelectPermsByUserId(){
        List<String> list = menuMapper.selectPermsByUserId(1L);
        System.out.println(list);
    }

}
