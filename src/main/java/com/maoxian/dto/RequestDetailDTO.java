package com.maoxian.dto;

import com.maoxian.pojo.RequestDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDetailDTO {
    private Integer mode;
    private List<RequestDetail> request;
}
