package com.maoxian.gateway.exceprion;

/**
 * 请求异常
 *
 * @author Lin
 * @date 2023/11/23 14:30
 */
public class RequestException extends RuntimeException {
    public RequestException() {
    }

    public RequestException(String message) {
        super(message);
    }
}
