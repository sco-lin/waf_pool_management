package com.maoxian.gateway.controller;

import com.maoxian.gateway.dto.UserBaseInfoDTO;
import com.maoxian.gateway.dto.UserInfoDTO;
import com.maoxian.gateway.dto.UserPasswordDTO;
import com.maoxian.gateway.exceprion.BusinessException;
import com.maoxian.gateway.exceprion.RequestException;
import com.maoxian.gateway.pojo.User;
import com.maoxian.gateway.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Lin
 * @date 2023/10/5 13:28
 */
@RestController
@RequestMapping("/user")
@PreAuthorize("hasAuthority('user')")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 查询所有用户的信息
     *
     * @return 查询结果
     */
    @GetMapping
    public List<UserInfoDTO> queryUser() {
        return userService.findUserInfoList();
    }

    /**
     * 注册用户
     *
     * @param user 用户信息
     */
    @PostMapping("/register")
    public void register(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        if (username == null || username.isEmpty()) {
            throw new RequestException("用户名不能为空");
        }
        if (password == null || password.isEmpty()) {
            throw new RequestException("密码不能为空");
        }
        if (email == null || email.isEmpty()) {
            throw new RequestException("邮箱不能为空");
        }
        userService.addOrModifyUser(user);
    }

    /**
     * 更新用户信息
     *
     * @param userBaseInfoDTO 用户基本信息
     */
    @PutMapping
    public void modifyUserBaseInfo(@RequestBody UserBaseInfoDTO userBaseInfoDTO) {
        Long id = userBaseInfoDTO.getId();
        if (id == null) {
            throw new BusinessException("id不能为空");
        }
        userService.modifyUserBaseInfo(userBaseInfoDTO);
    }

    /**
     * 修改密码
     *
     * @param userPasswordDTO 密码
     */
    @PutMapping("/password")
    public void modifyPassword(@RequestBody UserPasswordDTO userPasswordDTO) {
        Long id = userPasswordDTO.getId();
        String oldPassword = userPasswordDTO.getOldPassword();
        String newPassword = userPasswordDTO.getNewPassword();
        if (id == null) {
            throw new BusinessException("id不能为空");
        }
        if (oldPassword == null || oldPassword.isEmpty() ||
                newPassword == null || newPassword.isEmpty()) {
            throw new BusinessException("密码不能为空");
        }
        userService.modifyPassword(userPasswordDTO);
    }

    /**
     * 根据id删除用户
     *
     * @param id 删除条件
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
