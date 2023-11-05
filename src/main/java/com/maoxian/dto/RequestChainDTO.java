package com.maoxian.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestChainDTO {

    private String status;

    private Integer time;

    private Integer wafId;

    private List<RequestChainDTO> children;
}
