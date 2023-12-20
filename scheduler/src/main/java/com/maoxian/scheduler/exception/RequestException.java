package com.maoxian.scheduler.exception;

/**
 * 请求异常
 * @author Lin
 * @date 2023/12/19 22:46
 */
public class RequestException extends RuntimeException {
    public RequestException() {
    }

    public RequestException(String message) {
        super(message);
    }
}
