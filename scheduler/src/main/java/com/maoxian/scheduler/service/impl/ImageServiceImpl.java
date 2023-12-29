package com.maoxian.scheduler.service.impl;

import com.github.dockerjava.api.model.Image;
import com.maoxian.scheduler.exception.BusinessException;
import com.maoxian.scheduler.mapper.ImageMapper;
import com.maoxian.scheduler.mapper.WafMapper;
import com.maoxian.scheduler.pojo.ImageInfo;
import com.maoxian.scheduler.service.DockerService;
import com.maoxian.scheduler.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<ImageInfo> findImageList() {
        return imageMapper.selectList();
    }

    @Override
    public void addImage(InputStream imageStream) {

        List<Image> beforeImages = dockerService.listImages();
        Boolean flag = dockerService.importImage(imageStream);
        if (!flag){
            throw new BusinessException("导入镜像失败");
        }
        List<Image> afterImages = dockerService.listImages();

        // 根据导入镜像前后镜像的区别得到导入镜像的信息
        List<String> beforeImageIds = beforeImages.stream().map(Image::getId).collect(Collectors.toList());
        for (Image afterImage : afterImages) {
            if (!beforeImageIds.contains(afterImage.getId())) {
                String[] split = afterImage.getRepoTags()[0].split(":");
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setName(split[0]);
                imageInfo.setTag(split[1]);
                imageInfo.setImageId(afterImage.getId().split(":")[1]);

                // 将镜像信息存入数据库
                imageMapper.insert(imageInfo);
            }
        }
    }

    @Override
    public void deleteImageById(Long id) {
        ImageInfo imageInfo = imageMapper.select(id);
        if (imageInfo == null){
            throw new BusinessException("镜像不存在");
        }
        String imageId = imageInfo.getImageId();
        Boolean flag = dockerService.removeImage(imageId);
        if (!flag){
            throw new BusinessException("删除镜像存在");
        }
        imageMapper.deleteById(id);
    }
}
