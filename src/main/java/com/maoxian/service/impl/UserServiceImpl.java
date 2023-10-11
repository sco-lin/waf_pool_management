package com.maoxian.service.impl;

import com.maoxian.exceprion.BusinessExp;
import com.maoxian.mapper.PermMapper;
import com.maoxian.mapper.UserMapper;
import com.maoxian.request.QueryRequest;
import com.maoxian.vo.QueryResult;
import com.maoxian.pojo.User;
import com.maoxian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    //查询用户信息的bean
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermMapper permMapper;

    //密码加密和验证的bean
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<String> queryPermByUserId(Integer userId) {
        if (userId == 0) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            permMapper.queryPermByUserId(user.getId());
        }
        return permMapper.queryPermByUserId(userId);
    }

    @Override
    public QueryResult<User> queryUser(QueryRequest queryRequest) {

        Integer pageNum = queryRequest.getPageNum();
        Integer pageSize = queryRequest.getPageSize();
        List<User> users;
        Integer total = userMapper.selectForCount();

        if (total == 0) {
            users = Collections.emptyList();
        } else {
            int start = (pageNum - 1) * pageSize;
            users = userMapper.queryUserList(start, pageSize);
        }

        return new QueryResult<User>(pageNum, pageSize, users, total);
    }

    @Override
    public void saveOrUpdateUser(User user) {

        //更新用户
        if (user.getId() != null) {
            int count = userMapper.updateUser(user);
            if (count == 0) {
                throw new BusinessExp("用户不存在");
            }
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
    }

    @Override
    public void deleteUser(Integer id) {
        int count = userMapper.deleteUser(id);
        if (count == 0) {
            throw new BusinessExp("删除失败");
        }
    }
}
