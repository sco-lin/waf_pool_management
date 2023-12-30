package com.maoxian.scheduler.mapper;

import com.maoxian.scheduler.pojo.WafMonitor;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Lin
 * @date 2023/12/21 15:04
 */
@Mapper
public interface WafMonitorMapper {
    /**
     * 增加waf状态
     *
     * @param wafStatus waf状态信息
     * @return 更改的行数
     */
    int insert(WafMonitor wafStatus);
}
