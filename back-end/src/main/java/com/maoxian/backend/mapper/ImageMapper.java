package com.maoxian.backend.mapper;

import com.maoxian.backend.pojo.ImageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Lin
 * @date 2023/12/30 10:04
 */
@Mapper
public interface ImageMapper {

    /**
     * 增加镜像
     *
     * @param imageInfo 镜像信息
     * @return 更改行数
     */
    int insert(ImageInfo imageInfo);

    /**
     * 更新镜像
     *
     * @param imageInfo 镜像信息
     * @return 更改行数
     */
    int update(ImageInfo imageInfo);

    /**
     * 通过id删除镜像
     *
     * @param id id
     * @return 更改行数
     */
    int deleteById(Long id);

    /**
     * 查询所有镜像
     *
     * @return 镜像信息
     */
    List<ImageInfo> selectList();

    /**
     * 通过id查询镜像
     *
     * @param id 镜像id
     * @return 镜像信息
     */
    ImageInfo select(Long id);
}
