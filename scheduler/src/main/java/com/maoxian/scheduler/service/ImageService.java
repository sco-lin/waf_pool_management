package com.maoxian.scheduler.service;

import com.maoxian.scheduler.pojo.Image;

import java.util.List;

/**
 * @author Lin
 * @date 2023/12/20 17:03
 */
public interface ImageService {

    /**
     * 查询所有镜像
     * @return 所有镜像信息
     */
    List<Image> findImageList();

    /**
     * 使用指定镜像启动一个容器
     * @param name 容器名
     * @param imageId 镜像id
     */
    void startContainer(String name,Long imageId);
}
