package com.maoxian.controller;

import com.maoxian.request.QueryRequest;
import com.maoxian.vo.JsonResult;
import com.maoxian.vo.QueryResult;
import com.maoxian.pojo.User;
import com.maoxian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    @PreAuthorize("hasAuthority('user:insert')")
    public JsonResult signUp(@RequestBody User user) {
        userService.saveOrUpdateUser(user);
        return JsonResult.success();
    }

    @PostMapping("query")
    @PreAuthorize("hasAuthority('user:select')")
    public JsonResult queryUser(@RequestBody QueryRequest queryRequest) {
        QueryResult<User> queryResult = userService.queryUser(queryRequest);
        return JsonResult.success(queryResult);
    }

    @GetMapping("perm/{userId}")
    public JsonResult queryPerm(@PathVariable Integer userId){
        List<String> perm = userService.queryPermByUserId(userId);
        return JsonResult.success(perm);
    }

    @PostMapping("update")
    @PreAuthorize("hasAuthority('user:update')")
    public JsonResult updateUser(@RequestBody User user) {
        userService.saveOrUpdateUser(user);
        return JsonResult.success();
    }

    @GetMapping("delete/{id}")
    @PreAuthorize("hasAuthority('user:delete')")
    public JsonResult deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return JsonResult.success();
    }
}
