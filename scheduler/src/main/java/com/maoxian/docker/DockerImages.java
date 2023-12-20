package com.maoxian.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Info;

/**
 * @author Lin
 * @date 2023/12/17 23:03
 */
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
