package com.maoxian.mapper;

import com.maoxian.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 查询所有用户信息：分页+模糊查询
     *
     * @param start    开始位置
     * @param pageSize 查询个数
     * @param username 模糊查询字段
     * @param email    模糊查询字段
     * @return 所有用户信息
     */
    List<User> selectList(int start, int pageSize, String username, String email);

    /**
     * 查询记录数：模糊查询
     *
     * @param username 模糊查询字段
     * @param email    模糊查询字段
     * @return 记录数
     */
    Integer count(String username, String email);

    /**
     * 通过id查询用户
     *
     * @param id 查询条件
     * @return 用户信息
     */
    User selectById(int id);

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
     */
    int insert(User user);

    /**
     * 更新用户
     *
     * @param user 用户信息
     */
    int update(User user);

    /**
     * 通过i删除用户
     *
     * @param id 删除条件
     */
    int deleteById(Integer id);
}
