package com.maoxian.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页查询结果类
 *
 * @param <T>
 * @author Lin
 * @date 2023/10/5 13:28
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
