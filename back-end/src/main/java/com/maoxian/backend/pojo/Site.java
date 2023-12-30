package com.maoxian.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Lin
 * @date 2023/12/27 21:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Site {
    private Long id;
    private String domain;
    private String server;
    private Integer mode;
    private String comment;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
