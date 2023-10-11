package com.maoxian.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryRequest {

    private Integer pageNum = 1;

    private Integer pageSize = 5;
}
