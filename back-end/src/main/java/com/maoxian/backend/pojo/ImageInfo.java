package com.maoxian.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Lin
 * @date 2023/12/30 10:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageInfo {
    private Long id;
    private String name;
    private String tag;
    private String imageId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
