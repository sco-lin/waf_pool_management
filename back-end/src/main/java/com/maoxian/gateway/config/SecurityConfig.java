package com.maoxian.gateway.config;

import com.maoxian.gateway.filter.JwtAuthenticationTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Lin
 * @date 2023/9/21 15:30
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    /**
     * token验证过滤器
     */
    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    /**
     * 认证入口点，处理身份验证失败的组件
     */
    private final AuthenticationEntryPoint authenticationEntryPoint;

    /**
     * 拒绝访问处理，处理访问无权限资源时，拒绝访问的情况
     */
    private final AccessDeniedHandler accessDeniedHandler;

    /**
     * 认证配置
     */
    private final AuthenticationConfiguration auth;

    public SecurityConfig(JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter, AuthenticationEntryPoint authenticationEntryPoint, AccessDeniedHandler accessDeniedHandler, AuthenticationConfiguration auth) {
        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
        this.auth = auth;
    }


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
                .antMatchers("/login", "/email/**").anonymous()
                //除上面的所有请求全部都需要认证
                .anyRequest().authenticated();

        //关闭默认注销接口
        http.logout().disable();

        //把自定义token校验过滤器添加到过滤器链中
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //配置异常处理器，处理认证失败和授权失败
        http.exceptionHandling()
                //认证失败处理器
                .authenticationEntryPoint(authenticationEntryPoint)
                //授权失败处理器
                .accessDeniedHandler(accessDeniedHandler);

        //允许跨域
        http.cors();

        return http.build();
    }
}
