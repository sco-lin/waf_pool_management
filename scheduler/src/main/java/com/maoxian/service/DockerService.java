package com.maoxian.service;


import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;

import java.io.InputStream;
import java.util.List;

/**
 * @author Lin
 * @date 2023/12/17 23:03
 */
public interface DockerService {

    /**
     * 容器列表
     * @param showAll 展示所有
     */
    List<Container> listContainer(Boolean showAll);

    /**
     * 查询容器日志信息
     * @param containerId 容器id
     * @param tail 行数
     */
    List<String> logContainer(String containerId,Integer tail);

    /**
     * 查询容器详情 TODO 返回类型待定
     * @param containId 容器id
     */
    String infoContainer(String containId);

    /**
     * 删除容器
     * @param containerId 容器id
     */
    Boolean removeContainer(String containerId);

    /**
     * 停止容器
     * @param containerId 容器id
     */
    Boolean stopContainer(String containerId);

    /**
     * 启动容器
     * @param containerId 容器id
     */
    Boolean startContainer(String containerId);

    /**
     * 创建容器 TODO 传入参数待定
     */
    Boolean createContainer();

    /**
     * 镜像列表 TODO 参数待定
     */
    List<Image> images();

    /**
     * 镜像详情
     * @param imageId 镜像id
     */
    String imageInfo(String imageId);

    /**
     * 导入镜像文件
     * @param tarInputStream 镜像tar文件流
     */
    Boolean importImage(InputStream tarInputStream);

    /**
     * 构建镜像 TODO 构建参数待定
     */
    Boolean buildImage();

    /**
     * 推送本地镜像到harbor仓库 TODO 参数待定
     */
    Boolean pushImageToHarbor();

}
