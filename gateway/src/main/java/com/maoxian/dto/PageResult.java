package com.maoxian.dto;

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
public class PageResult<T> {
    private Integer pageNum;
    private Integer pageSize;
    private List<T> list;
    private Integer total;
}
