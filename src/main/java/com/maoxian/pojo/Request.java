package com.maoxian.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    private Integer id;

    private String uuid;

    private String method;

    private String sourceIp;

    private String url;

    private LocalDateTime startTime;

    private Integer time;

    private String status;
}
