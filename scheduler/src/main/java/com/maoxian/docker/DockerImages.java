package com.maoxian.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Info;
import org.springframework.stereotype.Component;


public class DockerImages {
    DockerUtils dockerUtils;

    public DockerImages(DockerUtils dockerUtils) {
        this.dockerUtils = dockerUtils;
    }

    public void testInfo() {
        DockerClient dockerClient = dockerUtils.getDockerClient();
        Info info = dockerClient.infoCmd().exec();
        System.out.println(info);
    }
}
