package com.maoxian.mapper;

import com.maoxian.pojo.Waf;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WafMapper {

    Waf queryWafById(Long id);
}
