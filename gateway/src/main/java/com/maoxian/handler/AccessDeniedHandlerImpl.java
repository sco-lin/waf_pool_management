package com.maoxian.handler;

import com.alibaba.fastjson.JSON;
import com.maoxian.utils.WebUtil;
import com.maoxian.utils.JsonResult;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限不足处理
 *
 * @author Lin
 * @date 2023/9/21 15:30
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException){

        //{code:401, msg:"权限不足"}
        JsonResult result = JsonResult.fail(HttpStatus.UNAUTHORIZED.value(), "权限不足");
        String json = JSON.toJSONString(result);

        //处理异常
        WebUtil.renderString(response, json);
    }
}
