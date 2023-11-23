package com.maoxian.mapper;

import com.maoxian.pojo.Waf;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WafMapper {

    /**
     * 查询记录数
     *
     * @return 记录数
     */
    Integer count();

    /**
     * 查询指定状态waf的数量
     *
     * @param status waf状态
     * @return waf数量
     */
    Integer countForStatus(Integer status);

    /**
     * 查询waf列表
     *
     * @return waf列表
     */
    List<Waf> selectList();

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
     * @param id id
     */
    int deleteById(Integer id);
}
