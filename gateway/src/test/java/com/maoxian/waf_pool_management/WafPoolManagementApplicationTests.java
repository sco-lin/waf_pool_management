package com.maoxian.waf_pool_management;

import com.maoxian.gateway.dto.UserPasswordDTO;
import com.maoxian.gateway.mapper.PermMapper;
import com.maoxian.gateway.mapper.RequestRecordMapper;
import com.maoxian.gateway.mapper.ScheduleRecordMapper;
import com.maoxian.gateway.mapper.UserMapper;
import com.maoxian.gateway.pojo.ScheduleRecord;
import com.maoxian.gateway.pojo.User;
import com.maoxian.gateway.pojo.Waf;
import com.maoxian.gateway.service.LoginService;
import com.maoxian.gateway.service.UserService;
import com.maoxian.gateway.service.WafService;
import com.maoxian.gateway.utils.EmailUtil;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class WafPoolManagementApplicationTests {

    @Autowired
    RequestRecordMapper requestMapper;

    @Autowired
    LoginService loginService;

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

    @Autowired
    private ModelMapper modelMapper;

    private String emailRegular = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$";

    private String ipRegular = "^(((\\d)|([1-9]\\d)|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d)|([1-9]\\d)|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))$";

    @Autowired
    private ScheduleRecordMapper scheduleRecordMapper;

    @Test
    public void testModelMapper() {
        UserPasswordDTO userPasswordDTO = new UserPasswordDTO(1, "a", "b", "1");
        User user = modelMapper.map(userPasswordDTO, User.class);
        System.out.println(user);
    }

    @Test
    public void test() {
        Integer a = 1;
        Integer b = 1;
        System.out.println(a == b);
        System.out.println(a.equals(b));
    }

    @Test
    public void testRequestChain() {
        Random random = new Random();
        for (int i = 1; i < 1001; i++) {
            List<ScheduleRecord> byRequestId = scheduleRecordMapper.selectList(i);
            int count1 = byRequestId.size() / 3;
            if (count1 < 1) {
                break;
            }
            int count = random.nextInt(count1);
            if (count < 1) {
                count = 1;
            }
            for (int j = 0; j < count; j++) {
                Long id = byRequestId.get(j).getId();
//                requestChainMapper.updateChain(id);
            }
        }
    }

    @Test
    public void testWaf() {
        List<Waf> wafPageResult = wafService.findWafList();
        System.out.println(wafPageResult);
    }

    @Test
    public void getUUID() {
        String s = UUID.randomUUID().toString();
        String s2 = UUID.randomUUID().toString();
        System.out.println(s);
        System.out.println(s2);
    }

    @Test
    public void testRegular() {
        System.out.println("zls@163.com".matches(emailRegular));
        System.out.println("sdff@af".matches(emailRegular));
        System.out.println("12.54.18.24".matches(ipRegular));
        System.out.println("15.19.168.256".matches(ipRegular));
    }

    @Test
    public void testSelectPermsByUserId() {
        List<String> list = permMapper.selectByUserId(1);
        System.out.println(list);
    }

    @Test
    public void testUser() {
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

    @Test
    public void testSendTemplateEmail() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("email", "zls2434474199@163.com");
        variables.put("key", "dddddd");
//        variables.put("verifyCode","12345");
//        variables.put("valid",5);
        emailUtil.sendHtmlEmail("zls2434474199@163.com", "测试", "activateUrl.html", variables);
    }

}
