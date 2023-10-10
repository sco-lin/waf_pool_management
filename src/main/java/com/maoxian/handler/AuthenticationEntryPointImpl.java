package com.maoxian.handler;

import com.alibaba.fastjson.JSON;
import com.maoxian.utils.WebUtil;
import com.maoxian.vo.JsonResult;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证失败处理
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {

        //{code:401, msg:"用户认证失败"}
        JsonResult result = JsonResult.fail(HttpStatus.FORBIDDEN.value(), "用户认证失败");
        String json = JSON.toJSONString(result);

        //处理异常
        WebUtil.renderString(response, json);
    }
}
