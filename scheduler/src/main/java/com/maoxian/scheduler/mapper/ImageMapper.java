package com.maoxian.scheduler.mapper;

import com.maoxian.scheduler.pojo.ImageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Lin
 * @date 2023/12/19 23:49
 */
@Mapper
public interface ImageMapper {

    int insert(ImageInfo imageInfo);

    int update(ImageInfo imageInfo);

    int deleteById(Long id);

    /**
     * 查询所有镜像
     * @return 镜像信息
     */
    List<ImageInfo> selectList();

    /**
     * 通过id查询镜像
     * @param id 镜像id
     * @return 镜像信息
     */
    ImageInfo select(Long id);
}
