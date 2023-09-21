package com.maoxian.mapper;

import com.maoxian.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 查询所有用户
     *
     * @return 所有用户信息
     */
    List<User> queryUserList();

    /**
     * 通过id查询用户
     *
     * @param id 用户id
     * @return 用户信息
     */
    User queryUserById(int id);

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    User queryUserByUsername(String username);

    /**
     * 增加用户
     *
     * @param user 用户
     */
    void addUser(User user);
}
