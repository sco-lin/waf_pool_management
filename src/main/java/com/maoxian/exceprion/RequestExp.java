package com.maoxian.exceprion;

/**
 * 请求异常
 */
public class RequestExp extends RuntimeException {
    public RequestExp() {
    }

    public RequestExp(String message) {
        super(message);
    }
}
