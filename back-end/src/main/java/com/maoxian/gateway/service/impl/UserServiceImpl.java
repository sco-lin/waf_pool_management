package com.maoxian.gateway.service.impl;

import com.maoxian.gateway.dto.UserBaseInfoDTO;
import com.maoxian.gateway.dto.UserInfoDTO;
import com.maoxian.gateway.dto.UserPasswordDTO;
import com.maoxian.gateway.exceprion.BusinessException;
import com.maoxian.gateway.mapper.PermMapper;
import com.maoxian.gateway.mapper.RoleMapper;
import com.maoxian.gateway.mapper.UserMapper;
import com.maoxian.gateway.pojo.User;
import com.maoxian.gateway.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lin
 * @date 2023/10/5 13:28
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final RoleMapper roleMapper;

    private final PermMapper permMapper;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    public UserServiceImpl(UserMapper userMapper, RoleMapper roleMapper, PermMapper permMapper, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.permMapper = permMapper;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserInfoDTO> findUserInfoList() {
        List<UserInfoDTO> userInfoDTOList = new ArrayList<>();

        List<User> users = userMapper.selectList();
        for (User user : users) {
            List<String> roles = roleMapper.selectByUserId(user.getId());
            List<String> permissions = permMapper.selectByUserId(user.getId());
            UserInfoDTO userInfoDTO = new UserInfoDTO(user.getId(), user.getUsername(),
                    user.getEmail(), user.getStatus(), roles, permissions);
            userInfoDTOList.add(userInfoDTO);
        }

        return userInfoDTOList;
    }

    @Override
    public void addOrModifyUser(User user) {
        // 增加用户
        if (user.getId() == null) {
            User userByUsername = userMapper.selectByUsername(user.getUsername());
            if (userByUsername != null) {
                throw new BusinessException("用户名已存在");
            }
            //密码加密
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            int count = userMapper.insert(user);
            if (count == 0) {
                throw new BusinessException("增加用户失败");
            }
        } else {
            // 更新用户
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            int count = userMapper.update(user);
            if (count == 0) {
                throw new BusinessException("更新失败");
            }
        }
    }

    @Override
    public void modifyUserBaseInfo(UserBaseInfoDTO userBaseInfoDTO) {
        User user = modelMapper.map(userBaseInfoDTO, User.class);
        addOrModifyUser(user);
    }

    @Override
    public void modifyPassword(UserPasswordDTO userPasswordDTO) {
        User user = userMapper.selectById(userPasswordDTO.getId());
        boolean flag = passwordEncoder.matches(userPasswordDTO.getOldPassword(), user.getPassword());
        if (!flag) {
            throw new BusinessException("旧密码错误");
        }
        new User();
        user.setId(user.getId());
        user.setPassword(userPasswordDTO.getNewPassword());
        addOrModifyUser(user);
    }

    @Override
    public void deleteUserById(Long id) {
        int count = userMapper.deleteById(id);
        if (count == 0) {
            throw new BusinessException("删除用户失败");
        }
    }

}
