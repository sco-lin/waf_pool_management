package com.maoxian.service;

import com.maoxian.vo.JsonResult;
import com.maoxian.vo.QueryResult;
import com.maoxian.pojo.User;

public interface UserService {

    /**
     * 查询用户
     *
     * @param userQueryResult user查询结果
     * @return 查询结果
     */
    JsonResult queryUser(QueryResult<User> userQueryResult);

    /**
     * 添加或更新用户
     * @param user 用户信息
     * @return 操作结果
     */
    JsonResult saveOrUpdateUser(User user);

    /**
     * 删除用户
     * @param id id
     * @return 删除结果
     */
    JsonResult deleteUser(Long id);
}
