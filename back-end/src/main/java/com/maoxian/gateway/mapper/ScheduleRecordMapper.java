package com.maoxian.gateway.mapper;

import com.maoxian.gateway.pojo.ScheduleRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Lin
 * @date 2023/11/5 9:14
 */
@Mapper
public interface ScheduleRecordMapper {

    /**
     * 通过parentId查询请求链列表
     *
     * @param requestId 夫id
     * @return 请求链列表
     */
    List<ScheduleRecord> selectList(Long requestId);
}
