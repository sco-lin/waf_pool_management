package com.maoxian.scheduler.service;

import com.maoxian.scheduler.pojo.ImageInfo;

import java.io.InputStream;
import java.util.List;

/**
 * @author Lin
 * @date 2023/12/20 17:03
 */
public interface ImageService {

    /**
     * 查询所有镜像
     *
     * @return 所有镜像信息
     */
    List<ImageInfo> findImageList();

    /**
     * 增加镜像
     *
     * @param imageStream 镜像流
     */
    void addImage(InputStream imageStream);

    /**
     * 删除镜像
     *
     * @param id 镜像id
     */
    void deleteImageById(Long id);
}
