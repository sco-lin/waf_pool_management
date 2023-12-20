package com.maoxian.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Lin
 * @date 2023/11/5 9:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestRecordDTO {
    private String uuid;
    private String method;
    private String sourceIp;
    private String url;
    private LocalDateTime startTime;
    private Integer time;
    private String status;
}
