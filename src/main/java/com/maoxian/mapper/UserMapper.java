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
    List<User> queryUserList(int start, int pageSize);

    Integer selectForCount();

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
     * 通过email查询用户
     * @param email 邮箱
     * @return 用户信息
     */
    User queryUserByEmail(String email);

    /**
     * 增加用户
     *
     * @param user 用户
     */
    int addUser(User user);

    /**
     * 更新用户
     *
     * @param user 用户
     */
    int updateUser(User user);

    /**
     * 删除用户
     *
     * @param id id
     */
    int deleteUser(Integer id);
}
