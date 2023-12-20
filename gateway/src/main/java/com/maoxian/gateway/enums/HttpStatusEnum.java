package com.maoxian.gateway.enums;

import lombok.Getter;

/**
 * HTTP状态返回枚举
 *
 * @author Lin
 * @date 2023/11/23 14:30
 */
@Getter
public enum HttpStatusEnum {
    SUCCESS(200, "操作成功"),
    CREATED(201, "对象创建成功"),
    ACCEPTED(202, "请求已被接受"),
    NO_CONTENT(204, "操作成功，但是没有返回数据"),
    MOVED_PERM(301, "资源以被移除"),
    SEE_OTHER(303, "重定向"),
    NOT_MODIFIED(304, "资源没有被修改"),
    BAD_REQUEST(400, "参数列表错误（缺少，格式不匹配）"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "访问受限，授权过期"),
    NOT_FOUND(404, "资源不存在"),
    BAD_METHOD(405, "不允许的http方法"),
    CONFLICT(409, "资源冲突，或资源被锁"),
    UNSUPPORTED_TYPE(415, "不支持的数据类型"),
    ERROR(500, "系统内部错误"),
    NOT_IMPLEMENTED(501, "接口未实现"),
    WARN(601, "系统警告信息");

    private final Integer code;
    private final String message;

    HttpStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
