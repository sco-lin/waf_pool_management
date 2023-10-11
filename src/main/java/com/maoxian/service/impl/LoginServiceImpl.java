package com.maoxian.service.impl;

import com.maoxian.exceprion.BusinessExp;
import com.maoxian.mapper.RoleMapper;
import com.maoxian.pojo.User;
import com.maoxian.request.LoginRequest;
import com.maoxian.service.LoginService;
import com.maoxian.utils.JwtUtil;
import com.maoxian.utils.RedisCache;
import com.maoxian.vo.LoginVo;
import com.maoxian.vo.UserInfoVo;
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
    public LoginVo login(LoginRequest loginRequest) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        if (username == null || password == null) {
            throw new BusinessExp("用户名和密码不能为空");
        }

        //用户身份认证，认证失败则抛出异常
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //认证成功：使用user_id生成jwt
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        User user = loginUser.getUser();
        Integer userId = user.getId();
        String jwt = JwtUtil.createJWT(userId.toString());

        //把完整的用户信息存入redis，user_id作为key
        redisCache.setCacheObject("login:" + userId, loginUser);

        //查询用户角色信息
        List<String> roles = roleMapper.queryRoleByUserId(userId);

        //返回数据
        UserInfoVo userInfoVo = new UserInfoVo(userId, user.getUsername(), user.getEmail(), roles, loginUser.getPermissions());

        return new LoginVo(jwt, userInfoVo);
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
