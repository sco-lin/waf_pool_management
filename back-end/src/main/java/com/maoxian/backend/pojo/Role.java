package com.maoxian.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Lin
 * @date 2023/10/5 13:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    private Long id;
    private String name;
    private String roleKey;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
