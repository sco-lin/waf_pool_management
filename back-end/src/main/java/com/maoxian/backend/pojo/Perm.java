package com.maoxian.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Lin
 * @date 2023/9/21 15:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Perm {

    private Long id;
    private String name;
    private String perm;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
