package com.maoxian.scheduler.controller;

import com.maoxian.scheduler.exception.RequestException;
import com.maoxian.scheduler.pojo.ImageInfo;
import com.maoxian.scheduler.service.ImageService;
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

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * 查询所有镜像
     *
     * @return 镜像列表
     */
    @GetMapping
    public List<ImageInfo> queryImage() {
        return imageService.findImageList();
    }

    /**
     * 上传镜像
     *
     * @param image docker镜像
     */
    @PostMapping("/upload")
    public void addImage(@RequestParam("image") MultipartFile image) {
        if (image == null || image.isEmpty()) {
            throw new RequestException("文件为空");
        }
        InputStream imageStream;
        try {
            imageStream = image.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException("获取文件流失败");
        }
        imageService.addImage(imageStream);
    }

    /**
     * 删除镜像
     *
     * @param id 镜像id
     */
    @DeleteMapping("/{id}")
    public void deleteImageById(@PathVariable Long id) {
        imageService.deleteImageById(id);
    }
}
