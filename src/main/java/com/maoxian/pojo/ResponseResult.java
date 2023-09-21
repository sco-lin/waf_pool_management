package com.maoxian.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 响应类
 *
 * @param <T> 数据
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResponseResult<T> {

    //状态码
    private Integer code;

    //提示信息，存在错误时，前端可以获取该字段进行提示
    private String msg;

    //查询到的结果
    private T data;

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
