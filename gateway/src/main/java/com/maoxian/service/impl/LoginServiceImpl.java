package com.maoxian.service.impl;

import com.maoxian.dto.UserInfoDTO;
import com.maoxian.exceprion.BusinessExp;
import com.maoxian.mapper.RoleMapper;
import com.maoxian.pojo.User;
import com.maoxian.service.LoginService;
import com.maoxian.utils.JwtUtil;
import com.maoxian.utils.RedisCache;
import com.maoxian.dto.LoginInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    //认证管理的bean，处理认证和授权
    @Autowired
    private AuthenticationManager authenticationManager;

    //redis缓存
    @Autowired
    private RedisCache redisCache;

    //查询用户的角色信息的bean
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public LoginInfoDTO login(String username, String password, String verifyCode) {

        //用户身份认证，认证失败则抛出异常
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        User user = loginUser.getUser();

//        //验证码校验
//        String code = redisCache.getCacheObject("verifyCode:" + user.getEmail());
//        if (!verifyCode.equals(code)) {
//            throw new BusinessExp("验证码错误");
//        }

        //认证成功：使用user_id生成jwt
        Integer userId = user.getId();
        String jwt = JwtUtil.createJWT(userId.toString());

        //把完整的用户信息存入redis，user_id作为key
        redisCache.setCacheObject("login:" + userId, loginUser);

        //查询用户角色信息
        List<String> roles = roleMapper.selectByUserId(userId);
        if (roles.isEmpty()) {
            throw new BusinessExp("查询角色失败");
        }

        //返回数据
        UserInfoDTO userInfoDTO = new UserInfoDTO(userId, user.getUsername(), user.getEmail(), user.getStatus(), roles, loginUser.getPermissions());

        return new LoginInfoDTO(jwt, userInfoDTO);
    }

    @Override
    public void logout() {

        //从SecurityContextHolder中获取用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();

        //删除redis中的值
        redisCache.deleteObject("login:" + userId);
    }
}