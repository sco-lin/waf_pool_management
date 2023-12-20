package com.maoxian.gateway.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Lin
 * @date 2023/9/21 15:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Perm {

    private Integer id;
    private String name;
    private String perm;
}
