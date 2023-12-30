package com.maoxian.backend.service.impl;

import com.maoxian.backend.exceprion.BusinessException;
import com.maoxian.backend.mapper.PermMapper;
import com.maoxian.backend.mapper.UserMapper;
import com.maoxian.backend.pojo.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 加载用户详细信息，权限信息
 *
 * @author Lin
 * @date 2023/9/21 15:30
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    private final PermMapper permMapper;

    public UserDetailsServiceImpl(UserMapper userMapper, PermMapper permMapper) {
        this.userMapper = userMapper;
        this.permMapper = permMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查询用户信息
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        //查询用户权限信息
        List<String> list = permMapper.selectByUserId(user.getId());
        if (list.isEmpty()) {
            throw new BusinessException("查询权限失败");
        }

        //把数据封装成UserDetails返回
        return new LoginUser(user, list);
    }
}
