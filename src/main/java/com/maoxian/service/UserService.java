package com.maoxian.service;

import com.maoxian.request.QueryRequest;
import com.maoxian.vo.QueryResult;
import com.maoxian.pojo.User;

import java.util.List;

public interface UserService {

    /**
     * 查询用户
     *
     * @param queryRequest@return userQueryResult 查询结果
     */
    QueryResult<User> queryUser(QueryRequest queryRequest);

    /**
     * 查询权限
     * @return 权限信息
     */
    List<String> queryPermByUserId(Integer userId);

    /**
     * 添加或更新用户
     *
     * @param user 用户信息
     */
    void saveOrUpdateUser(User user);

    /**
     * 删除用户
     *
     * @param id id
     */
    void deleteUser(Integer id);
}
