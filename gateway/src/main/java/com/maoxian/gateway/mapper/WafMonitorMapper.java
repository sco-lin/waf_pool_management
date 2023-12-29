package com.maoxian.gateway.mapper;

import com.maoxian.gateway.pojo.WafMonitor;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author Lin
 * @date 2023/11/10 12:42
 */
@Mapper
public interface WafMonitorMapper {

    /**
     * 通过id查询waf
     *
     * @param wafId 查询条件
     * @return waf信息
     */
    WafMonitor selectByWafId(Long wafId);

    /**
     * 增加waf状态
     *
     * @param wafStatus waf状态信息
     */
    int insert(WafMonitor wafStatus);

    /**
     * 更新waf
     *
     * @param wafStatus waf状态信息
     */
    int update(WafMonitor wafStatus);

    /**
     * 通过id删除waf
     *
     * @param id id
     */
    int deleteById(Integer id);
}
