package com.maoxian.mapper;

import com.maoxian.pojo.Image;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Lin
 * @date 2023/12/19 23:49
 */
@Mapper
public interface ImageMapper {

    Integer insert(Image image);

    Integer update(Image image);
}
