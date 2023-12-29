package com.maoxian.scheduler.service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DockerServiceTest {

    @Autowired
    private DockerClient dockerClient;
    @Autowired
    private DockerService dockerService;

    @Test
    void listContainer() {
        List<Container> containers = dockerService.listContainer(true);
        for (Container container : containers) {
            System.out.println(container);
        }
    }

    @Test
    void logContainer() {
    }

    @Test
    void infoContainer() {
    }

    @Test
    void removeContainer() {
    }

    @Test
    void stopContainer() {
    }

    @Test
    void startContainer() {
    }

    @Test
    void createContainer() {
        CreateContainerCmd containerCmd = dockerClient.createContainerCmd("605c77e624dd");
        containerCmd
                .withName("nginx")
                .exec();
    }

    @Test
    void images() {
        List<Image> images = dockerService.listImages();
        System.out.println(images);
    }

    @Test
    void imageInfo() {
    }

    @Test
    void importImage() {
    }

    @Test
    void buildImage() {
    }

    @Test
    void pushImageToHarbor() {
    }
}