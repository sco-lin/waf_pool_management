package com.maoxian.service.impl;

import com.maoxian.mapper.UserMapper;
import com.maoxian.pojo.LoginUser;
import com.maoxian.pojo.ResponseResult;
import com.maoxian.pojo.User;
import com.maoxian.service.LoginService;
import com.maoxian.utils.JwtUtil;
import com.maoxian.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseResult login(User user) {

        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //如果认证没通过，给出对应的提示
        if (authenticate == null) {
            throw new RuntimeException("登录失败");
        }

        //如果认证通过了，使用user_id生成jwt，并存入ResponseResult返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);

        //把完整的用户信息存入redis，user_id作为key
        redisCache.setCacheObject("login:" + userId, loginUser);

        return new ResponseResult(200, "登录成功", map);
    }

    @Override
    public ResponseResult signUp(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            userMapper.addUser(user);
        } catch (Exception e) {
            return new ResponseResult(300, "注册失败");
        }
        return new ResponseResult(200, "注册成功");

    }

    @Override
    public ResponseResult logout() {
        //获取SecurityContextHolder的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        //删除redis中的值
        redisCache.deleteObject("login:" + userId);
        return new ResponseResult<>(200, "登出成功");
    }
}
