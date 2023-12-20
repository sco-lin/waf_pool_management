package com.maoxian.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Lin
 * @date 2023/10/19 18:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestRecord {

    private Long id;
    private String uuid;
    private String method;
    private String srcIp;
    private String desIp;
    private String url;
    private Integer mode;
    private Long time;
    private Boolean pass;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
