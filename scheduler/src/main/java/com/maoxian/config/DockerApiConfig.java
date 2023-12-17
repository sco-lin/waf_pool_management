package com.maoxian.config;


import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.PushImageCmd;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.maoxian.docker.UnCompress;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

@Slf4j
@Configuration
@PropertySource("classpath:docker.properties")
public class DockerApiConfig {
    @Value("${docker-url}")
    private String dockerUrl;


    /**
     * 创建docker客户端
     */
    @Bean
    public DockerClient dockerClient() {
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(dockerUrl)
                .build();
        DockerClient dockerClient = DockerClientBuilder.getInstance(config).build();
        log.info("docker api 初始化成功");
        return dockerClient;
    }

}
