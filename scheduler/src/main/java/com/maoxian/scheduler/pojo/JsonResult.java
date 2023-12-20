package com.maoxian.scheduler.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.maoxian.scheduler.enums.HttpStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * json响应类
 * @author Lin
 * @date 2023/12/18 23:35
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult {
    //状态码
    private Integer code;
    //提示信息
    private String msg;
    //响应状态
    private String status;
    //查询到的结果
    private Object data;

    public static JsonResult success() {
        return new JsonResult(200, "操作成功", "success", null);
    }

    public static JsonResult success(Object data) {
        return new JsonResult(200, "操作成功", "success", data);
    }

    public static JsonResult fail() {
        return new JsonResult(500, "操作失败", "fail", null);
    }

    public static JsonResult fail(String msg) {
        return new JsonResult(500, msg, "fail", null);
    }

    public static JsonResult fail(int code, String msg) {
        return new JsonResult(code, msg, "fail", null);
    }

    public static JsonResult fail(HttpStatusEnum httpStatus) {
        return fail(httpStatus.getCode(), httpStatus.getMessage());
    }
}
