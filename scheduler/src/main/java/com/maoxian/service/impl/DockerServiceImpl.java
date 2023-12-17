package com.maoxian.service.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.*;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.command.LogContainerResultCallback;
import com.maoxian.service.DockerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class DockerServiceImpl implements DockerService {

    @Autowired
    private DockerClient dockerClient;

    @Override
    public List<Container> listContainer(Boolean showAll) {
        ListContainersCmd listContainersCmd = dockerClient.listContainersCmd();
        ListContainersCmd containersCmd = listContainersCmd.withShowAll(showAll);
        return containersCmd.exec();
    }

    @Override
    public List<String> logContainer(String containerId, Integer tail) {
        List<String> logs = new LinkedList<>();
        LogContainerCmd logContainerCmd = dockerClient.logContainerCmd(containerId);
        logContainerCmd.withStdOut(true).withStdErr(true);
        // 显示行数
        logContainerCmd.withTail(tail);
        logContainerCmd.exec(new ResultCallback<Frame>() {
            @Override
            public void onStart(Closeable closeable) {

            }

            @Override
            public void onNext(Frame object) {
                logs.add(object.toString());
            }

            @Override
            public void onError(Throwable throwable) {
                log.error("查询日志失败：" + containerId);
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void close() throws IOException {

            }
        });
        return logs;
    }

    @Override
    public String infoContainer(String containId) {
        InspectContainerCmd inspectContainerCmd = dockerClient.inspectContainerCmd(containId);
        InspectContainerResponse response = inspectContainerCmd.exec();
        return response.toString();
    }

    @Override
    public Boolean removeContainer(String containerId) {
        RemoveContainerCmd removeContainerCmd = dockerClient.removeContainerCmd(containerId);
        try{
            removeContainerCmd.exec();
        }catch (Exception e){
            log.error("删除容器失败：" + containerId);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean stopContainer(String containerId) {
        StopContainerCmd stopContainerCmd = dockerClient.stopContainerCmd(containerId);
        try{
            stopContainerCmd.exec();
        }catch (Exception e){
            log.error("停止容器失败：" + containerId);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean startContainer(String containerId) {
        StartContainerCmd startContainerCmd = dockerClient.startContainerCmd(containerId);
        try{
            startContainerCmd.exec();
        }catch (Exception e){
            log.error("启动容器失败：" + containerId);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean createContainer() {
        return null;
    }

    @Override
    public List<Image> images() {
        return null;
    }

    @Override
    public String imageInfo(String imageId) {
        InspectImageCmd inspectImageCmd = dockerClient.inspectImageCmd(imageId);
        InspectImageResponse response = inspectImageCmd.exec();
        return response.toString();
    }

    @Override
    public Boolean importImage(InputStream tarInputStream) {
        dockerClient.loadImageCmd(tarInputStream).exec();
        return Boolean.TRUE;
    }

    @Override
    public Boolean buildImage() {
        return null;
    }

    @Override
    public Boolean pushImageToHarbor() {
        return null;
    }
}
