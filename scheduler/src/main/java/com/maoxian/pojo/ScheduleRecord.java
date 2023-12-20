package com.maoxian.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Lin
 * @date 2023/12/17 23:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRecord {

    private Long id;
    private Long time;
    private Boolean pass;
    private Long wafId;
    private Long requestId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
