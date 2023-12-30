package com.maoxian.backend.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.maoxian.backend.enums.HttpStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * json响应类
 *
 * @author Lin
 * @date 2023/9/21 15:30
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult {
    private Integer code;
    private String msg;
    private String status;
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
