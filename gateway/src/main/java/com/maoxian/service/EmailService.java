package com.maoxian.service;

/**
 * @author Lin
 * @date 2023/10/25 23:27
 */
public interface EmailService {

    /**
     * 发送邮箱验证码
     *
     * @param targetEmail 目标邮箱
     */
    void sendEmailCode(String targetEmail);

    /**
     * 发送激活邮件
     * @param targetEmail 目标邮箱
     */
    void sendEmailActivateUrl(String targetEmail);

    /**
     * 邮箱激活
     * @param email 邮箱
     * @param key key
     */
    void activate(String email, String key);
}
