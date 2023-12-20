package com.maoxian.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Lin
 * @date 2023/12/17 23:03
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    //认证配置
    @Autowired
    private AuthenticationConfiguration auth;


    /**
     * 创建BCryptPasswordEncoder对象注入容器
     *
     * @return BCryptPasswordEncoder 用于密码加密与验证
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 创建AuthenticationManager对象注入容器
     *
     * @return authenticationManagerBean 处理身份验证请求和用户授权
     * @throws Exception 创建失败
     */
    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return auth.getAuthenticationManager();
    }

    /**
     * 配置安全策略和访问控制规则
     *
     * @param http HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception 异常
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                //关闭csrf（跨站请求伪造）保护
                .csrf().disable()
                //使用无状态（stateless）的认证方式，不通过session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //配置请求的授权规则
                .authorizeRequests()
                //对于登录接口和邮箱验证接口，允许匿名访问
                .antMatchers("/**").anonymous()
                //除上面的所有请求全部都需要认证
                .anyRequest().authenticated();

        //关闭默认注销接口
        http.logout().disable();

        //允许跨域
        http.cors();

        return http.build();
    }
}
