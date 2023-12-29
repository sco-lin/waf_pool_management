package com.maoxian.scheduler.config;


import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Lin
 * @date 2023/12/17 23:03
 */
@Configuration
@PropertySource("classpath:scheduler.properties")
public class DockerConfig {

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
        return DockerClientBuilder.getInstance(config).build();
    }

}
