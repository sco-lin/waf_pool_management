package com.maoxian.gateway.service;

/**
 * @author Lin
 * @date 2023/10/25 23:27
 */
public interface EmailService {

    /**
     * 发送邮箱验证码
     *
     * @param email 目标邮箱
     */
    void sendEmailCode(String email);
}
