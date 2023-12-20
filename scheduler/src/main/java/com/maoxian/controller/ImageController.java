package com.maoxian.controller;

import com.maoxian.exception.RequestException;
import com.maoxian.service.DockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Lin
 * @date 2023/12/19 22:46
 */
@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private DockerService dockerService;
    /**
     * 上传镜像
     * @param image docker镜像
     */
    @PostMapping("/upload")
    public void addWafByImage(@RequestParam("image") MultipartFile image){
        if (image == null||image.isEmpty()){
            throw new RequestException("文件为空");
        }
        InputStream imageStream;
        try {
            imageStream = image.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException("获取文件流失败");
        }
        dockerService.importImage(imageStream);
    }
}
