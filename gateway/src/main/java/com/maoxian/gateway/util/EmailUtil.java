package com.maoxian.gateway.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 邮件工具类
 *
 * @author Lin
 * @date 2023/10/25 23:27
 */
@Slf4j
@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    /**
     * 发送html邮件
     *
     * @param to      目标邮箱
     * @param subject 主题
     * @param text    内容
     */
    public void sendHtmlEmail(String to, String subject, String text) {
        // 创建邮件发送者对象，支持文本、html、附件等
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 帮助构建MIME邮件消息，简化构建MIME邮件的过程
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        // 设置主题、内容、发送方、目标邮箱
        try {
            helper.setSubject(subject);
            helper.setText(text, true);
            helper.setFrom(fromEmail);
            helper.setTo(to);
        } catch (MessagingException e) {
            log.info("发送邮箱验证码失败，目标邮箱：{}",to);
        }

        // 发送邮件
        mailSender.send(mimeMessage);
    }
}
