package com.maoxian.mapper;

import com.maoxian.pojo.Waf;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WafMapper {

    /**
     * 查询记录数：模糊查询
     *
     * @param name 模糊查询字段
     * @param ip 模糊查询字段
     * @return 记录数
     */
    Integer count(String name, String ip);

    /**
     * 查询waf列表：分页+模糊查询
     *
     * @param start   开始位置
     * @param pageSize 查询个数
     * @param name 模糊查询字段
     * @param ip 模糊查询字段
     * @return waf列表
     */
    List<Waf> selectList(int start, int pageSize, String name, String ip);

    /**
     * 通过id查询waf
     *
     * @param id 查询条件
     * @return waf信息
     */
    Waf selectById(Integer id);

    /**
     * 增加waf
     *
     * @param waf waf信息
     */
    int insert(Waf waf);

    /**
     * 更新waf
     *
     * @param waf waf信息
     */
    int update(Waf waf);

    /**
     * 通过id删除waf
     *
     * @param id
     */
    int deleteById(Integer id);
}
