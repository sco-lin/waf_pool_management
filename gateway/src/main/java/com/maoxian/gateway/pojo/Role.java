package com.maoxian.gateway.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Lin
 * @date 2023/10/5 13:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    private Integer id;
    private String name;
    private String roleKey;
}
