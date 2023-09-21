package com.maoxian.mapper;

import com.maoxian.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 查询所有用户
     * @return
     */
    List<User> queryUserList();

    /**
     * 通过id查询用户
     * @param id
     * @return
     */
    User queryUserById(int id);

    /**
     * 通过userName查询用户
     * @param username
     * @return
     */
    User queryUserByUsername(String username);

    /**
     * 增加用户
     *
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    User deleteUser(int id);
}
