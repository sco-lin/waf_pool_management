package com.maoxian.scheduler.exception;

/**
 * 业务异常
 * @author Lin
 * @date 2023/12/17 23:03
 */
public class BusinessException extends RuntimeException {

    public BusinessException() {

    }

    public BusinessException(String message) {
        super(message);
    }
}
