package com.maoxian.gateway.dto;

import com.maoxian.gateway.pojo.ScheduleRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Lin
 * @date 2023/11/5 9:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchedoleRecordDTO {
    private Integer mode;
    private List<ScheduleRecord> request;
}
