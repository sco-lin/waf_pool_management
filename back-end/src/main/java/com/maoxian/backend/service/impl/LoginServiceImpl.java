package com.maoxian.backend.service.impl;

import com.maoxian.backend.dto.LoginInfoDTO;
import com.maoxian.backend.dto.UserInfoDTO;
import com.maoxian.backend.exceprion.BusinessException;
import com.maoxian.backend.mapper.RoleMapper;
import com.maoxian.backend.pojo.User;
import com.maoxian.backend.service.LoginService;
import com.maoxian.backend.util.JwtUtil;
import com.maoxian.backend.util.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lin
 * @date 2023/12/29 20:16
 */
@Service
public class LoginServiceImpl implements LoginService {

    /**
     * 认证管理的bean，处理认证和授权
     */
    private final AuthenticationManager authenticationManager;

    private final RedisCache redisCache;

    private final RoleMapper roleMapper;

    public LoginServiceImpl(AuthenticationManager authenticationManager, RedisCache redisCache, RoleMapper roleMapper) {
        this.authenticationManager = authenticationManager;
        this.redisCache = redisCache;
        this.roleMapper = roleMapper;
    }

    @Override
    public LoginInfoDTO login(String username, String password, String verifyCode) {

        //用户身份认证，认证失败则抛出异常
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        User user = loginUser.getUser();

        String code = redisCache.getCacheObject("verifyCode:" + user.getEmail());
        if (!verifyCode.equals(code)) {
            throw new BusinessException("验证码错误");
        }

        //认证成功：使用user_id生成jwt
        Long userId = user.getId();
        String jwt = JwtUtil.createJwt(userId.toString());

        //把完整的用户信息存入redis，user_id作为key
        redisCache.setCacheObject("login:" + userId, loginUser);

        //查询用户角色信息
        List<String> roles = roleMapper.selectByUserId(userId);
        if (roles.isEmpty()) {
            throw new BusinessException("查询角色失败");
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
        Long userId = loginUser.getUser().getId();

        //删除redis中的值
        redisCache.deleteObject("login:" + userId);
    }
}
