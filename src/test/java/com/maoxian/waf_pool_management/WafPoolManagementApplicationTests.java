package com.maoxian.waf_pool_management;

import com.maoxian.mapper.PermMapper;
import com.maoxian.mapper.LogMapper;
import com.maoxian.pojo.Log;
import com.maoxian.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WafPoolManagementApplicationTests {

    @Autowired
    LogMapper logMapper;

    @Autowired LoginService loginService;


    @Test
    void contextLoads() {
        Log log = logMapper.selectRequest(1);
        System.out.println(log.toString());

    }

    @Autowired
    private PermMapper permMapper;

    @Test
    public void testSelectPermsByUserId(){
        List<String> list = permMapper.queryPermByUserId(1);
        System.out.println(list);
    }

}
