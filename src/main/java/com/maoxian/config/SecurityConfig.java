package com.maoxian.config;

import com.maoxian.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 配置SpringSecurity
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)//启用全局方法级别的安全性配置，启用方法级别的前置注解
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //jwt认证token过滤器，处理请求携带的token
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    //认证入口点，处理身份验证失败的组件
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    //拒绝访问处理，处理访问无权限资源时，拒绝访问的情况
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    /**
     * 配置安全策略和访问控制规则
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf（跨站请求伪造）保护
                .csrf().disable()
                //使用无状态（stateless）的认证方式，不通过session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //配置请求的授权规则
                .authorizeRequests()
                //对于登录接口，允许匿名访问
                .antMatchers("/user/login").anonymous()
                //除上面的所有请求全部都需要认证
                .anyRequest().authenticated();

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
    }
}
