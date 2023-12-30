package com.maoxian.backend.service;

import com.maoxian.backend.pojo.ImageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Lin
 * @date 2023/12/30 10:04
 */
public interface ImageService {

    /**
     * 查询所有镜像
     *
     * @return 所有镜像信息
     */
    List<ImageInfo> findImageList();

    /**
     * 增加镜像
     *
     * @param image 镜像
     */
    void addImage(MultipartFile image);

    /**
     * 删除镜像
     *
     * @param id 镜像id
     */
    void deleteImageById(Long id);
}
