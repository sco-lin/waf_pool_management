package com.maoxian.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Lin
 * @date 2023/11/5 9:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestRecordDTO {
    private Long id;
    private String uuid;
    private String method;
    private String srcIp;
    private String desIp;
    private String url;
    private Integer mode;
    private Long time;
    private Boolean pass;
}
