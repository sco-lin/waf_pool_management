package com.maoxian.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

/**
 * 邮件工具类
 *
 * @author Lin
 * @date 2023/10/25 23:27
 */
@Component
public class EmailUtil {

    // 用于发送邮件
    @Autowired
    private JavaMailSender mailSender;

    // thymeleaf模板引擎的核心类，处理模板文件，替换变量
    @Autowired
    private TemplateEngine templateEngine;

    // 发送方email
    @Value("${spring.mail.username}")
    private String email;

    /**
     * 发送解析后的html邮件
     *
     * @param to      目标邮箱
     * @param subject 主题
     * @param text    内容
     */
    public void sendEmail(String to, String subject, String text) {
        // 创建邮件发送者对象，支持文本、html、附件等
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 帮助构建MIME邮件消息，简化构建MIME邮件的过程
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        // 设置主题、内容、发送方、目标邮箱
        try {
            helper.setSubject(subject);
            helper.setText(text, true);
            helper.setFrom(email);
            helper.setTo(to);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // 发送邮件
        mailSender.send(mimeMessage);
    }

    /**
     * 发送未解析的html邮件
     *
     * @param to           目标邮箱
     * @param subject      主题
     * @param templatePath 模板文件路径
     * @param variables    模板中需要的变量
     */
    public void sendHtmlEmail(String to, String subject, String templatePath, Map<String, Object> variables) {
        // 创建thymeleaf的Context对象，将模板中需要的变量设置到context
        Context context = new Context();
        context.setVariables(variables);

        //  处理模板文件，发送解析后的邮件
        String text = templateEngine.process(templatePath, context);
        sendEmail(to, subject, text);
    }
}
