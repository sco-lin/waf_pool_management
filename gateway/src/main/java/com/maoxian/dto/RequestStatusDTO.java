package com.maoxian.dto;

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
public class RequestStatusDTO {
    private Integer total;
    private Integer malicious;
}
