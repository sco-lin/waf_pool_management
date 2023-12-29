package com.maoxian.scheduler.service.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.*;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.api.model.Statistics;
import com.maoxian.scheduler.mapper.ImageMapper;
import com.maoxian.scheduler.mapper.WafMapper;
import com.maoxian.scheduler.mapper.WafMonitorMapper;
import com.maoxian.scheduler.service.DockerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * @author Lin
 * @date 2023/12/17 23:03
 */
@Service
@Slf4j
public class DockerServiceImpl implements DockerService {

    @Autowired
    private DockerClient dockerClient;

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private WafMapper wafMapper;

    @Autowired
    private WafMonitorMapper wafMonitorMapper;

    @Override
    public List<Container> listContainer(Boolean showAll) {
        ListContainersCmd listContainersCmd = dockerClient.listContainersCmd();
        ListContainersCmd containersCmd = listContainersCmd.withShowAll(showAll);
        return containersCmd.exec();
    }

    @Override
    public Boolean removeContainer(String containerId) {
        RemoveContainerCmd removeContainerCmd = dockerClient.removeContainerCmd(containerId);
        try {
            removeContainerCmd.exec();
        } catch (Exception e) {
            log.error("删除容器失败：{}", containerId);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean stopContainer(String containerId) {
        StopContainerCmd stopContainerCmd = dockerClient.stopContainerCmd(containerId);
        try {
            stopContainerCmd.exec();
        } catch (Exception e) {
            log.error("停止容器失败：{}", containerId);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean startContainer(String containerId) {
        StartContainerCmd startContainerCmd = dockerClient.startContainerCmd(containerId);
        try {
            startContainerCmd.exec();
        } catch (Exception e) {
            log.error("启动容器失败：{}", containerId);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public String createContainer(String name, String imageId) {
        CreateContainerCmd createContainerCmd = dockerClient.createContainerCmd(imageId);
        CreateContainerResponse response;
        try {
            response = createContainerCmd.withName(name).exec();
        } catch (Exception e) {
            log.error("通过镜像创建容器失败：{}", imageId);
            return null;
        }
        return response.getId();
    }

    @Override
    public List<Image> listImages() {
        ListImagesCmd listImagesCmd = dockerClient.listImagesCmd();
        return listImagesCmd.exec();
    }

    @Override
    public Boolean importImage(InputStream tarInputStream) {
        try {
            dockerClient.loadImageCmd(tarInputStream).exec();
        } catch (Exception e) {
            log.error("导入镜像失败");
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean removeImage(String imageId) {
        try {
            dockerClient.removeImageCmd(imageId).exec();
        } catch (Exception e) {
            log.error("删除镜像失败");
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Async
    @Override
    public void statContainer(String containerId, ResultCallback<Statistics> callback) {
            dockerClient.statsCmd(containerId).withNoStream(true).exec(callback);
    }
}
