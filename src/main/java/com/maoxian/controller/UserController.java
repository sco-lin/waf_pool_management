package com.maoxian.controller;

import com.maoxian.vo.QueryVo;
import com.maoxian.pojo.User;
import com.maoxian.service.UserService;
import com.maoxian.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void signUp(@RequestBody User user) {
        userService.saveOrUpdateUser(user);
    }

    /**
     * 查询所有用户
     *
     * @param pageNum  第几页
     * @param pageSize 页面大小
     * @return 第几页、页面大小、查询结果、总数
     */
    @GetMapping
    public QueryVo<UserInfoVo> queryUser(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "5") Integer pageSize,
                                   @RequestParam(defaultValue = "") String search) {
        return userService.queryUser(pageNum, pageSize, search);
    }

    /**
     * 查询当前用户信息
     *
     * @return 用户信息
     */
    @GetMapping("info")
    public UserInfoVo userInfo() {
        return userService.userInfo(0);
    }

    /**
     * 更新用户信息
     *
     * @param user 新的用户信息
     */
    @PutMapping
    public void updateUser(@RequestBody User user) {
        userService.saveOrUpdateUser(user);
    }

    /**
     * 根据id删除用户
     *
     * @param id 用户id
     */
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    //TODO 未使用
    @PutMapping("role/{userId}")
    public void setRoleByUserId(@PathVariable Integer userId) {
        userService.setRoleByUserId(userId);
    }

    //TODO 未使用
    @GetMapping("perm/{userId}")
    public List<String> queryPerm(@PathVariable Integer userId) {
        return userService.queryPermByUserId(userId);
    }
}
