package com.maoxian.exception;

/**
 * 业务异常
 */
public class BusinessExp extends RuntimeException {

    public BusinessExp() {

    }

    public BusinessExp(String message) {
        super(message);
    }
}
