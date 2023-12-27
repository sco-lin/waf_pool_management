package com.maoxian.scheduler.service.impl;

import com.maoxian.scheduler.exception.BusinessException;
import com.maoxian.scheduler.mapper.ImageMapper;
import com.maoxian.scheduler.mapper.WafMapper;
import com.maoxian.scheduler.pojo.Image;
import com.maoxian.scheduler.pojo.Waf;
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

    @Autowired
    private WafMapper wafMapper;

    @Override
    public List<Image> findImageList() {
        return imageMapper.selectList();
    }

    @Override
    public void startContainer(String name, Long imageId) {
        Image image = imageMapper.select(imageId);
        if (image == null) {
            throw new BusinessException("镜像不存在");
        }
        String containerId = dockerService.createContainer(name, image.getImageId());
        // 插入数据库 TODO waf的属性未设置
        Waf waf = new Waf(null, name, null, null, null, false, 0, null, imageId, containerId, null, null);
        wafMapper.insert(waf);
    }
}
