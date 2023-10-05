package com.maoxian.service.impl;

import com.maoxian.exceprion.BusinessExp;
import com.maoxian.vo.JsonResult;
import com.maoxian.pojo.User;
import com.maoxian.service.LoginService;
import com.maoxian.utils.JwtUtil;
import com.maoxian.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    //认证管理的bean，处理认证和授权
    @Autowired
    private AuthenticationManager authenticationManager;

    //redis缓存
    @Autowired
    private RedisCache redisCache;

    @Override
    public JsonResult login(User user) {

        if (user.getUsername()==null||user.getPassword()==null){
            throw new BusinessExp("用户名和密码不能为空");
        }

        //用户身份认证，认证失败则抛出异常
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //认证成功：使用user_id生成jwt
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authenticate.getPrincipal();
        String userId = userDetailsImpl.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        //把完整的用户信息存入redis，user_id作为key
        redisCache.setCacheObject("login:" + userId, userDetailsImpl);

        return JsonResult.success(jwt);
    }

    @Override
    public JsonResult logout() {

        //从SecurityContextHolder中获取用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetailsImpl.getUser().getId();

        //删除redis中的值
        redisCache.deleteObject("login:" + userId);

        return JsonResult.success();
    }
}
