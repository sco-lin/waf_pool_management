package com.maoxian.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Lin
 * @date 2023/11/30 18:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WafIpDTO {
    private Integer id;
    private String ip;
}
