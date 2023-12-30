package com.maoxian.backend.mapper;

import com.maoxian.backend.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Lin
 * @date 2023/9/21 15:30
 */
@Mapper
public interface UserMapper {

    /**
     * 查询所有用户信息
     *
     * @return 所有用户信息
     */
    List<User> selectList();

    /**
     * 查询记录数
     *
     * @return 记录数
     */
    Integer count();

    /**
     * 通过id查询用户
     *
     * @param id 查询条件
     * @return 用户信息
     */
    User selectById(Long id);

    /**
     * 通过用户名查询用户
     *
     * @param username 查询条件
     * @return 用户信息
     */
    User selectByUsername(String username);

    /**
     * 通过email查询用户
     *
     * @param email 查询条件
     * @return 用户信息
     */
    User selectByEmail(String email);

    /**
     * 增加用户
     *
     * @param user 用户信息
     * @return 更改行数
     */
    int insert(User user);

    /**
     * 更新用户
     *
     * @param user 用户信息
     * @return 更改行数
     */
    int update(User user);

    /**
     * 通过id删除用户
     *
     * @param id 删除条件
     * @return 更改行数
     */
    int deleteById(Long id);
}
