package com.maoxian.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页查询结果类
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryResult<T> {

    private Integer pageNum = 1;

    private Integer pageSize = 5;

    private List<T> list;

    private Integer total;
}
