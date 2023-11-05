package com.maoxian.service.impl;

import com.maoxian.dto.UserPasswordDTO;
import com.maoxian.dto.UserInfoDTO;
import com.maoxian.exceprion.BusinessExp;
import com.maoxian.mapper.PermMapper;
import com.maoxian.mapper.RoleMapper;
import com.maoxian.mapper.UserMapper;
import com.maoxian.utils.RedisCache;
import com.maoxian.vo.PageResult;
import com.maoxian.pojo.User;
import com.maoxian.service.UserService;
import com.maoxian.dto.UserBaseInfoDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    // 模型映射器
    @Autowired
    private ModelMapper modelMapper;

    // redis缓存
    @Autowired
    private RedisCache redisCache;

    @Override
    public PageResult<UserInfoDTO> findUserInfo(Integer pageNum, Integer pageSize, String search) {
        String username = null;
        String email = null;
        String emailRegular = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$";

        // 通过正则表达式判断模糊查询的字段
        if (search.matches(emailRegular)) {
            email = search;
        } else {
            username = search;
        }

        List<User> users;
        List<String> roles;
        List<String> permissions;
        List<UserInfoDTO> userInfoDTOS = new ArrayList<>();

        // 查询总数
        Integer total = userMapper.count(username, email);

        if (total == 0) {
            users = Collections.emptyList();
        } else {
            // 查询用户列表
            int start = (pageNum - 1) * pageSize;
            users = userMapper.selectList(start, pageSize, username, email);
        }
        if (users.isEmpty()) {
            throw new BusinessExp("查询用户失败");
        }

        for (User user : users) {
            roles = roleMapper.selectByUserId(user.getId());
            if (roles.isEmpty()) {
                throw new BusinessExp("查询角色失败");
            }
            permissions = permMapper.selectByUserId(user.getId());
            if (permissions.isEmpty()) {
                throw new BusinessExp("查询权限失败");
            }
            UserInfoDTO userInfoDTO = new UserInfoDTO(user.getId(), user.getUsername(), user.getEmail(), roles, permissions);
            userInfoDTOS.add(userInfoDTO);
        }

        return new PageResult<>(pageNum, pageSize, userInfoDTOS, total);
    }

    @Override
    public void addUser(User user) {
        User userByUsername = userMapper.selectByUsername(user.getUsername());
        if (userByUsername != null) {
            throw new BusinessExp("用户名已存在");
        }
        //密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        int count = userMapper.insert(user);
        if (count == 0) {
            throw new BusinessExp("增加用户失败");
        }
    }

    @Override
    public void modifyUser(UserBaseInfoDTO userBaseInfoDTO) {
        if (userBaseInfoDTO.getId() != null) {
            User user = modelMapper.map(userBaseInfoDTO, User.class);
            int count = userMapper.update(user);
            if (count == 0) {
                throw new BusinessExp("更新失败");
            }
        }
    }

    @Override
    public void modifyUser(UserPasswordDTO userPasswordDTO) {
        Integer id = userPasswordDTO.getId();
        String oldPassword = userPasswordDTO.getOldPassword();
        String newPassword = userPasswordDTO.getNewPassword();
        String verifyCode = userPasswordDTO.getVerifyCode();

        User user = userMapper.selectById(id);
        if (user == null) {
            throw new BusinessExp("查询用户失败");
        }

        //验证码校验
        String code = redisCache.getCacheObject("verifyCode:" + user.getEmail());
        if (!verifyCode.equals(code)) {
            throw new BusinessExp("验证码错误");
        }

        // 验证旧密码
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new BusinessExp("旧密码错误");
        }

        //TODO 使用模型映射器时报错
        // 设置密码
        String encode = passwordEncoder.encode(newPassword);
        User updateUser = new User();
        updateUser.setId(id);
        updateUser.setPassword(encode);

        int count = userMapper.update(updateUser);
        if (count == 0) {
            throw new BusinessExp("更新失败");
        }
    }

    @Override
    public void deleteUserById(Integer id) {
        int count = userMapper.deleteById(id);
        if (count == 0) {
            throw new BusinessExp("删除用户失败");
        }
    }

    @Override
    public UserInfoDTO findUserInfoById(Integer userId) {
        User user;
        List<String> permissions;

        //查询当前用户信息
        if (userId <= 0) {
            LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            user = loginUser.getUser();
            permissions = loginUser.getPermissions();
        } else {
            //查询指定用户信息
            user = userMapper.selectById(userId);
            if (user == null) {
                throw new BusinessExp("查询用户失败");
            }
            permissions = permMapper.selectByUserId(userId);
            if (permissions.isEmpty()) {
                throw new BusinessExp("查询用户失败");
            }
        }
        Integer id = user.getId();
        String username = user.getUsername();
        String email = user.getEmail();
        List<String> roles = roleMapper.selectByUserId(id);
        if (roles.isEmpty()) {
            throw new BusinessExp("查询角色失败");
        }

        return new UserInfoDTO(id, username, email, roles, permissions);
    }

}
