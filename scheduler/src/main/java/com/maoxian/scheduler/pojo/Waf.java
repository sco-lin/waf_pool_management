package com.maoxian.scheduler.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Lin
 * @date 2023/12/17 23:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Waf {

    private Long id;
    private String name;
    private String ip;
    private Integer port;
    private String configUrl;
    private Boolean online;
    private Integer weight;
    private String description;
    private Long imageId;
    private String containerId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
