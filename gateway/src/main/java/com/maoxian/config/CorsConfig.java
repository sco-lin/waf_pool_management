package com.maoxian.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置SpringBoot跨域
 *
 * @author Lin
 * @date 2023/9/21 15:30
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry
                //允许跨域的路径：所有路径的请求
                .addMapping("/**")
                //允许跨域请求的域名：所有域名
                .allowedOriginPatterns("*")
                //允许在跨域请求中包含凭证信息，如cookie
                .allowCredentials(true)
                //允许的请求方式
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                //允许包含的请求头信息：任何请求头
                .allowedHeaders("*")
                //允许跨域时间
                .maxAge(3600);
    }
}
