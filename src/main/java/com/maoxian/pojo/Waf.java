package com.maoxian.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Waf {

    private Integer id;

    private String name;

    private String ip;

    private Integer port;

    private String configUrl;

    private String enable;

    private String status;

    private Double cpu;

    private Double memory;

    private LocalDateTime startTime;

    private Integer upTime;

    private String description;
}
