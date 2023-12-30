package com.maoxian.backend.exceprion;

/**
 * 业务异常
 *
 * @author Lin
 * @date 2023/10/5 13:28
 */
public class BusinessException extends RuntimeException {

    public BusinessException() {

    }

    public BusinessException(String message) {
        super(message);
    }
}
