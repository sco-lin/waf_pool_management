package com.maoxian.controller;

import com.maoxian.request.QueryRequest;
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

    @PostMapping("register")
    public void signUp(@RequestBody User user) {
        userService.saveOrUpdateUser(user);
    }

    @PostMapping("query")
    public QueryVo<User> queryUser(@RequestBody QueryRequest queryRequest) {
        return userService.queryUser(queryRequest);
    }

    @GetMapping("userinfo")
    public UserInfoVo userInfo() {
        return userService.userInfo(0);
    }

    @GetMapping("userinfo/{userId}")
    public UserInfoVo userInfoByUserId(@PathVariable Integer userId) {
        return userService.userInfo(userId);
    }

    @GetMapping("perm/{userId}")
    public List<String> queryPerm(@PathVariable Integer userId) {
        return userService.queryPermByUserId(userId);
    }

    @PutMapping("update")
    public void updateUser(@RequestBody User user) {
        userService.saveOrUpdateUser(user);
    }

    @DeleteMapping("remove/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @PutMapping ("role/{userId}")
    public void setRoleByUserId(@PathVariable Integer userId){
        userService.setRoleByUserId(userId);
    }
}
