package com.maoxian.gateway.controller;

import com.maoxian.gateway.dto.UserPasswordDTO;
import com.maoxian.gateway.dto.UserBaseInfoDTO;
import com.maoxian.gateway.dto.UserInfoDTO;
import com.maoxian.gateway.exceprion.BusinessException;
import com.maoxian.gateway.exceprion.RequestException;
import com.maoxian.gateway.dto.PageResult;
import com.maoxian.gateway.pojo.User;
import com.maoxian.gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lin
 * @date 2023/10/5 13:28
 */
@RestController
@RequestMapping("user")
@PreAuthorize("hasAuthority('user')")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册用户
     *
     * @param user 用户信息
     */
    @PostMapping("register")
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
        userService.addUser(user);
    }

    /**
     * 查询所有用户
     *
     * @param pageNum  第几页
     * @param pageSize 页面大小
     * @param search   模糊查询字段
     * @return 查询结果
     */
    @GetMapping
    public PageResult<UserInfoDTO> queryUser(@RequestParam(defaultValue = "1") Integer pageNum,
                                             @RequestParam(defaultValue = "5") Integer pageSize,
                                             @RequestParam(defaultValue = "") String search) {
        return userService.findUserInfo(pageNum, pageSize, search);
    }

    //TODO 需要优化

    /**
     * 查询用户信息
     *
     * @return 用户信息
     */
    @GetMapping("info")
    public UserInfoDTO userInfo() {
        return userService.findUserInfoById(0);
    }

    /**
     * 更新用户信息
     *
     * @param userBaseInfoDTO 用户的基本信息
     */
    @PutMapping
    public void updateUserInfo(@RequestBody UserBaseInfoDTO userBaseInfoDTO) {
        Integer id = userBaseInfoDTO.getId();
        if (id == null) {
            throw new BusinessException("id不能为空");
        }
        userService.modifyUser(userBaseInfoDTO);
    }

    /**
     * 修改密码
     *
     * @param userPasswordDTO 密码
     */
    @PutMapping("/password")
    public void updateUserPassword(@RequestBody UserPasswordDTO userPasswordDTO) {
        Integer id = userPasswordDTO.getId();
        String newPassword = userPasswordDTO.getNewPassword();
        if (id == null) {
            throw new BusinessException("id不能为空");
        }
        if (newPassword == null || newPassword.isEmpty()) {
            throw new BusinessException("新密码不能为空");
        }
        userService.modifyUser(userPasswordDTO);
    }

    /**
     * 重置密码
     *
     * @param userPasswordDTO 密码
     */
    @PutMapping("/reset")
    public void resetPassword(@RequestBody UserPasswordDTO userPasswordDTO) {
        String email = userPasswordDTO.getEmail();
        String newPassword = userPasswordDTO.getNewPassword();
        String verifyCode = userPasswordDTO.getVerifyCode();
        if (email == null || email.isEmpty()) {
            throw new BusinessException("email不能为空");
        }
        if (newPassword == null || newPassword.isEmpty()) {
            throw new BusinessException("新密码不能为空");
        }
        if (verifyCode == null || verifyCode.isEmpty()) {
            throw new BusinessException("验证码不能为空");
        }
        userService.modifyUser(userPasswordDTO);
    }

    /**
     * 根据id删除用户
     *
     * @param id 删除条件
     */
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
    }
}
