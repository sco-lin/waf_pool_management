package com.maoxian.service;

import com.maoxian.vo.QueryResult;
import com.maoxian.pojo.User;

public interface UserService {

    /**
     * 查询用户
     *
     * @param userQueryResult user查询结果
     * @return userQueryResult 查询结果
     */
    QueryResult<User> queryUser(QueryResult<User> userQueryResult);

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
    void deleteUser(Long id);
}
