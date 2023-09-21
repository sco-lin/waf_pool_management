package com.maoxian.service.impl;

import com.maoxian.mapper.MenuMapper;
import com.maoxian.pojo.LoginUser;
import com.maoxian.mapper.UserMapper;
import com.maoxian.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查询用户信息
        User user = userMapper.queryUserByUsername(username);
        if (user == null) {
            throw new RuntimeException("找不到用户");
        }


        //List<String> list = new ArrayList<>(Arrays.asList("test", "admin"));
        List<String> list = menuMapper.selectPermsByUserId(user.getId());

        //把数据封装成UserDetails返回
        return new LoginUser(user, list);
    }
}
