package com.maoxian.backend.dto;

import com.maoxian.backend.pojo.ScheduleRecord;
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
public class ScheduleRecordDTO {
    private Integer mode;
    private List<ScheduleRecord> request;
}
