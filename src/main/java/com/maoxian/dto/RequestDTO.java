package com.maoxian.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {

    private String uuid;

    private String method;

    private String sourceI;

    private String url;

    private LocalDateTime startTime;

    private String status;
}
