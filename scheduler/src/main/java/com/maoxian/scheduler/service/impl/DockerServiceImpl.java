package com.maoxian.scheduler.service.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.*;
import com.github.dockerjava.api.model.*;
import com.maoxian.scheduler.exception.BusinessException;
import com.maoxian.scheduler.mapper.ImageMapper;
import com.maoxian.scheduler.mapper.WafMapper;
import com.maoxian.scheduler.mapper.WafMonitorMapper;
import com.maoxian.scheduler.pojo.Waf;
import com.maoxian.scheduler.pojo.WafMonitor;
import com.maoxian.scheduler.service.DockerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        try {
            removeContainerCmd.exec();
        } catch (Exception e) {
            log.error("删除容器失败：" + containerId);
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
            log.error("停止容器失败：" + containerId);
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
            log.error("启动容器失败：" + containerId);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public String createContainer(String name, String imageId) {
        CreateContainerCmd containerCmd = dockerClient.createContainerCmd(imageId);
        return containerCmd.withName(name).exec().getId();
    }

    @Override
    public List<Image> images() {
        ListImagesCmd listImagesCmd = dockerClient.listImagesCmd();
        return listImagesCmd.exec();
    }

    @Override
    public String imageInfo(String imageId) {
        InspectImageCmd inspectImageCmd = dockerClient.inspectImageCmd(imageId);
        InspectImageResponse response = inspectImageCmd.exec();
        return response.toString();
    }

    @Override
    public void importImage(InputStream tarInputStream) {
        List<Image> beforeImages = images();
        dockerClient.loadImageCmd(tarInputStream).exec();
        List<Image> afterImages = images();

        List<String> beforeImageIds = beforeImages.stream().map(Image::getId).collect(Collectors.toList());

        for (Image afterImage : afterImages) {
            if (!beforeImageIds.contains(afterImage.getId())) {
                String[] split = afterImage.getRepoTags()[0].split(":");
                com.maoxian.scheduler.pojo.Image newImage = new com.maoxian.scheduler.pojo.Image(null, split[0], split[1], afterImage.getId().split(":")[1], null, null);
                // 存入数据库
                imageMapper.insert(newImage);
            }
        }
    }

    @Override
    public Boolean buildImage() {
        return null;
    }

    @Override
    public Boolean pushImageToHarbor() {
        return null;
    }

    @Override
    @Async
    @Scheduled(fixedDelay = 5000)
    public void monitorContainer() {
        List<Waf> wafList = wafMapper.selectListForStatus(1);
        if (wafList == null) {
            throw new BusinessException("没有waf在运行");
        }
        for (Waf waf : wafList) {
            String containerId = waf.getContainerId();
            dockerClient.statsCmd(containerId).withNoStream(true).exec(new ResultCallback<Statistics>() {
                @Override
                public void onStart(Closeable closeable) {

                }

                @Override
                public void onNext(Statistics object) {
                    CpuStatsConfig cpuStats = object.getCpuStats();
                    Long totalUsage = Objects.requireNonNull(cpuStats.getCpuUsage()).getTotalUsage();
                    Long systemCpuUsage = cpuStats.getSystemCpuUsage();
                    BigDecimal cpu = BigDecimal.valueOf(totalUsage * 1.0 / systemCpuUsage * 100);
                    System.out.println(cpu);

                    MemoryStatsConfig memoryStats = object.getMemoryStats();
                    Long memoryStatsUsage = memoryStats.getUsage();
                    Long limit = memoryStats.getLimit();
                    BigDecimal mem = BigDecimal.valueOf(memoryStatsUsage * 1.0 / limit * 100);
                    System.out.println(mem);

                    // 插入数据库
                    WafMonitor wafMonitor = new WafMonitor(null, cpu, mem, waf.getId(), null, null);
                    wafMonitorMapper.insert(wafMonitor);
                }

                @Override
                public void onError(Throwable throwable) {

                }

                @Override
                public void onComplete() {

                }

                @Override
                public void close() throws IOException {

                }
            });
        }
    }
}
