package com.maoxian.scheduler.service.impl;

import com.maoxian.scheduler.exception.BusinessException;
import com.maoxian.scheduler.mapper.ImageMapper;
import com.maoxian.scheduler.pojo.Image;
import com.maoxian.scheduler.service.ImageService;
import com.maoxian.scheduler.service.DockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lin
 * @date 2023/12/20 17:04
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private DockerService dockerService;

    @Override
    public List<Image> findImageList() {
        return imageMapper.selectList();
    }

    @Override
    public void startContainer(Long imageId) {
        Image image = imageMapper.select(imageId);
        if (image == null){
            throw new BusinessException("镜像不存在");
        }
        dockerService.createContainer();
    }
}
