package com.maoxian.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Lin
 * @date 2023/12/19 23:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WafMonitor {
    private Long id;
    private BigDecimal cpu;
    private BigDecimal memory;
    private Long wafId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
