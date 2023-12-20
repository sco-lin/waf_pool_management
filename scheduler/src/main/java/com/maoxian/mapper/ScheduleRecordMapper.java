package com.maoxian.mapper;

import com.maoxian.pojo.ScheduleRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Lin
 * @date 2023/12/17 23:03
 */
@Mapper
public interface ScheduleRecordMapper {

    /**
     * 通过parentId查询请求链列表
     *
     * @param requestId 夫id
     * @return 请求链列表
     */
    List<ScheduleRecord> selectList(Integer requestId);

    int insert(ScheduleRecord scheduleRecord);
}
