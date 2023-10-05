package com.maoxian.service.impl;

import com.maoxian.exceprion.BusinessExp;
import com.maoxian.mapper.UserMapper;
import com.maoxian.vo.JsonResult;
import com.maoxian.vo.QueryResult;
import com.maoxian.pojo.User;
import com.maoxian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    //查询用户信息的bean
    @Autowired
    private UserMapper userMapper;

    //密码加密和验证的bean
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public JsonResult queryUser(QueryResult<User> userQueryResult) {
        Integer total = userMapper.selectForCount();
        if (total == 0) {
            userQueryResult.setList(Collections.emptyList());
        } else {
            int start = (userQueryResult.getPageNum() - 1) * userQueryResult.getPageSize();
            List<User> users = userMapper.queryUserList(start, userQueryResult.getPageSize());
            userQueryResult.setList(users);
        }
        userQueryResult.setTotal(total);
        return JsonResult.success(userQueryResult);
    }

    @Override
    public JsonResult saveOrUpdateUser(User user) {

        //更新用户
        if (user.getId() != null) {
            userMapper.updateUser(user);
            return JsonResult.success();
        }

        //增加用户
        if (user.getUsername() == null) {
            throw new BusinessExp("用户名不能为空");
        }
        User userByUsername = userMapper.queryUserByUsername(user.getUsername());
        if (userByUsername != null) {
            throw new BusinessExp("用户名已存在");
        }
        //密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.addUser(user);
        return JsonResult.success();
    }

    @Override
    public JsonResult deleteUser(Long id) {
        userMapper.deleteUser(id);
        return JsonResult.success();
    }
}
