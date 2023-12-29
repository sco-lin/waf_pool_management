package com.maoxian.scheduler.service;


import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.api.model.Statistics;

import java.io.InputStream;
import java.util.List;

/**
 * @author Lin
 * @date 2023/12/17 23:03
 */
public interface DockerService {

    /**
     * 查询容器
     *
     * @param showAll 展示所有（包括未启动的）
     * @return 容器列表
     */
    List<Container> listContainer(Boolean showAll);

    /**
     * 删除容器
     *
     * @param containerId 容器id
     * @return 操作成功
     */
    Boolean removeContainer(String containerId);

    /**
     * 停止容器
     *
     * @param containerId 容器id
     * @return 操作成功
     */
    Boolean stopContainer(String containerId);

    /**
     * 启动容器
     *
     * @param containerId 容器id
     * @return 操作成功
     */
    Boolean startContainer(String containerId);

    /**
     * 创建容器
     *
     * @param name    容器名
     * @param imageId 镜像id
     * @return 容器id
     */
    String createContainer(String name, String imageId);

    /**
     * 查询镜像
     *
     * @return 镜像列表
     */
    List<Image> listImages();

    /**
     * 导入镜像文件
     *
     * @param tarInputStream 镜像tar文件流
     * @return 操作成功
     */
    Boolean importImage(InputStream tarInputStream);

    /**
     * 删除镜像
     *
     * @param imageId 镜像id
     * @return 删除成功
     */
    Boolean removeImage(String imageId);

    /**
     * 监控容器
     */
    void statContainer(String containerId, ResultCallback<Statistics> callback);

}
