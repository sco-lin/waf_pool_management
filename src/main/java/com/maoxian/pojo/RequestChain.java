package com.maoxian.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestChain {

    private Integer id;

    private String status;

    private Integer time;

    private Integer wafId;

    private Integer requestId;

    private Integer parentId;
}
