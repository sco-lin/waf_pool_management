package com.maoxian.controller;

import com.maoxian.vo.JsonResult;
import com.maoxian.vo.QueryResult;
import com.maoxian.pojo.User;
import com.maoxian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    @PreAuthorize("hasAuthority('user:insert')")
    public JsonResult signUp(@RequestBody User user) {
        return userService.saveOrUpdateUser(user);
    }

    @PostMapping("query")
    @PreAuthorize("hasAuthority('user:select')")
    public JsonResult queryUser(@RequestBody QueryResult<User> userQueryResult){
        return userService.queryUser(userQueryResult);
    }

    @PostMapping("update")
    @PreAuthorize("hasAuthority('user:update')")
    public JsonResult updateUser(@RequestBody User user){
        return userService.saveOrUpdateUser(user);
    }

    @GetMapping("delete/{id}")
    @PreAuthorize("hasAuthority('user:delete')")
    public JsonResult deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }
}
