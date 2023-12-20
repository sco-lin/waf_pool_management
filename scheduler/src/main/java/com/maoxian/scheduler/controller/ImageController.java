package com.maoxian.scheduler.controller;

import com.maoxian.scheduler.exception.RequestException;
import com.maoxian.scheduler.pojo.Image;
import com.maoxian.scheduler.service.DockerService;
import com.maoxian.scheduler.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Lin
 * @date 2023/12/19 22:46
 */
@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private DockerService dockerService;

    @Autowired
    private ImageService imageService;

    /**
     * 上传镜像
     *
     * @param image docker镜像
     */
    @PostMapping("/upload")
    public void addWafByImage(@RequestParam("image") MultipartFile image) {
        if (image == null || image.isEmpty()) {
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

    /**
     * 查询所有镜像
     * @return 镜像列表
     */
    @GetMapping
    public List<Image> queryImage(){
        return imageService.findImageList();
    }

    public void startContainer(Long imageId){
        if (imageId == null){
            throw new RequestException("镜像id不能为空");
        }
        imageService.startContainer(imageId);
    }
}
