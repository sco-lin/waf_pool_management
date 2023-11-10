package com.maoxian.mapper;

import com.maoxian.pojo.Waf;
import com.maoxian.pojo.WafStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WafStatusMapper {

    /**
     * 通过id查询waf
     *
     * @param wafId 查询条件
     * @return waf信息
     */
    WafStatus selectByWafId(Integer wafId);

    /**
     * 增加waf状态
     *
     * @param wafStatus waf状态信息
     */
    int insert(WafStatus wafStatus);

    /**
     * 更新waf
     *
     * @param wafStatus waf状态信息
     */
    int update(WafStatus wafStatus);

    /**
     * 通过id删除waf
     *
     * @param id id
     */
    int deleteById(Integer id);
}
