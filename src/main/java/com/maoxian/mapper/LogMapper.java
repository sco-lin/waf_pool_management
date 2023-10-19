package com.maoxian.mapper;

import com.maoxian.pojo.Log;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogMapper {

    Log selectLog(Integer id);

    Log selectLogByParentId(Integer parentId);

    List<Integer> selectLogIdByUUID(String uuid);

    List<String> findUUIDList();
}
