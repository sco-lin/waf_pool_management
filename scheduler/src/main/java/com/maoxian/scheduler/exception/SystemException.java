package com.maoxian.scheduler.exception;

/**
 * @author Lin
 * @date 2023/12/17 23:03
 */
public class SystemException extends RuntimeException {
    public SystemException() {

    }

    public SystemException(String message) {
        super(message);
    }
}
