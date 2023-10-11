package com.maoxian.mapper;

import com.maoxian.pojo.Waf;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WafMapper {

    /**
     * 通过id查询waf
     *
     * @param id id
     * @return waf信息
     */
    Waf queryWafById(Integer id);

    /**
     * 增加waf
     *
     * @param waf waf信息
     */
    int addWaf(Waf waf);

    /**
     * 更新waf
     *
     * @param waf waf信息
     */
    int updateWaf(Waf waf);

    /**
     * 删除waf
     *
     * @param id id
     */
    int deleteWaf(Integer id);
}
