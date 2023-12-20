package com.maoxian.scheduler.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Lin
 * @date 2023/12/19 23:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    private Long id;
    private String name;
    private String tag;
    private String imageId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
