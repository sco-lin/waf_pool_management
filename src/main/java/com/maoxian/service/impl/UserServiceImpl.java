package com.maoxian.service.impl;

import com.maoxian.exceprion.BusinessExp;
import com.maoxian.mapper.PermMapper;
import com.maoxian.mapper.RoleMapper;
import com.maoxian.mapper.UserMapper;
import com.maoxian.request.QueryRequest;
import com.maoxian.vo.QueryVo;
import com.maoxian.pojo.User;
import com.maoxian.service.UserService;
import com.maoxian.vo.UserInfoVo;
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

    //查询用户角色信息的bean
    @Autowired
    private RoleMapper roleMapper;

    //权限信息的bean
    @Autowired
    private PermMapper permMapper;

    //密码加密和验证的bean
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<String> queryPermByUserId(Integer userId) {
        if (userId <= 0) {
            LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            permMapper.queryPermByUserId(loginUser.getUser().getId());
        }
        return permMapper.queryPermByUserId(userId);
    }

    @Override
    public QueryVo<User> queryUser(QueryRequest queryRequest) {

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

        return new QueryVo<>(pageNum, pageSize, users, total);
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

    @Override
    public UserInfoVo userInfo(Integer userId) {
        User user;
        List<String> permissions;
        //查询当前用户信息
        if (userId <= 0) {
            LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            user = loginUser.getUser();
            permissions = loginUser.getPermissions();
        } else {//查询指定用户信息
            user = userMapper.queryUserById(userId);
            permissions = permMapper.queryPermByUserId(userId);
        }
        Integer id = user.getId();
        String username = user.getUsername();
        String email = user.getEmail();
        List<String> roles = roleMapper.queryRoleByUserId(id);

        return new UserInfoVo(id, username, email, roles, permissions);
    }
}
