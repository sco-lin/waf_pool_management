package com.maoxian.docker;


import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.PushImageCmd;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * @author Lin
 * @date 2023/12/17 23:03
 */
@Slf4j
@PropertySource("classpath:docker.properties")
public class DockerUtils {
    @Value("${docker-url}")
    private String dockerUrl;
    @Value("${registry-url}")
    private String registryUrl;

    private DockerClient dockerClient;

    @PostConstruct
    public void init() {
        this.dockerClient = initDockerClient();
    }

    /**
     * 创建容器
     */
    public CreateContainerResponse createContainer(String name) {
        return dockerClient.createContainerCmd(name)
                .withName(name)
                .withExposedPorts(ExposedPort.tcp(80))
                .withExposedPorts(ExposedPort.parse("8080:80"))
                .exec();
    }

    /**
     * 推送镜像到仓库
     *
     * @param filePath 镜像路径
     * @return 推送后的完整镜像名
     */
    public String pushImageToRegistry(String filePath) throws Exception {
        // 准备文件和文件流
        File file = new File(filePath);
        InputStream inputStream = Files.newInputStream(file.toPath());
        log.debug("推送镜像到docker服务器");
        dockerClient.loadImageCmd(inputStream).exec();

        // 获取镜像信息
        String urlImageNameTag = UnCompress.getImageNameAndId(filePath);

        /*
        解析镜像信息
        从镜像文件中提取信息，包括镜像id，标签和名称
         */
        assert urlImageNameTag != null;
        String[] temp = Strings.split(urlImageNameTag, '%');
        String _imageId = temp[temp.length - 1];

        String[] temp2 = Strings.split(temp[0], ':');
        String _imageTag = temp2[temp2.length - 1];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < temp2.length - 1; i++) {
            sb.append(temp2[i]);
        }
        String _imageName = sb.toString();

        // 给镜像打标签
        log.debug("正在对镜像进行标签" + _imageName);
        dockerClient.tagImageCmd(_imageId, registryUrl + _imageName, _imageTag).exec();
        String fullName = registryUrl + _imageName + ":" + _imageTag;

        // 推送镜像到docker仓库
        log.debug("正在推送镜像到仓库"+fullName);
        PushImageCmd pushImageCmd = dockerClient.pushImageCmd(fullName);
        pushImageCmd.start().awaitCompletion();

        return fullName;
    }

    /**
     * 初始化docker客户端
     */
    private DockerClient initDockerClient() {
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(dockerUrl)
                .build();
        return DockerClientBuilder.getInstance(config).build();
    }

    /**
     * 获取docker客户端
     */
    public DockerClient getDockerClient() {
        log.info("dockerUrl:" + dockerUrl);
        return dockerClient;
    }
}
