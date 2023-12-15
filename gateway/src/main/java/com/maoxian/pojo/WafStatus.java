package com.maoxian.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WafStatus {
    private Integer id;
    private Double cpu;
    private Double memory;
    private LocalDateTime startTime;
    private Integer upTime;
    private Integer wafId;
}
