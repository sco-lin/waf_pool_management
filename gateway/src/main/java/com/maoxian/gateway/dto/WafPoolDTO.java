package com.maoxian.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Lin
 * @date 2023/11/23 14:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WafPoolDTO {
    private Integer total;
    private Integer online;
}
