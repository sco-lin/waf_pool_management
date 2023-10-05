package com.maoxian.handler;

import com.maoxian.exceprion.BusinessExp;
import com.maoxian.vo.JsonResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class CommonExpHandler {

    /**
     * 业务异常，提示错误信息
     *
     * @param e 错误
     * @return 响应类
     */
    @ExceptionHandler(BusinessExp.class)
    @ResponseBody
    public JsonResult handler(BusinessExp e) {
        e.printStackTrace();
        return JsonResult.fail(e.getMessage());
    }


    /**
     * 认证异常，处理认证失败
     *
     * @param e 错误
     * @return 响应类
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public JsonResult handler(AuthenticationException e) {
        e.printStackTrace();
        return JsonResult.fail(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }

    /**
     * 拒绝访问异常，处理权限不足
     *
     * @param e 错误
     * @return 响应类
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public JsonResult handler(AccessDeniedException e) {
        e.printStackTrace();
        return JsonResult.fail(HttpStatus.FORBIDDEN.value(), e.getMessage());
    }

    /**
     * 运行时异常，处理除认证授权外的异常
     *
     * @param e 错误
     * @return 响应类
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public JsonResult handler(RuntimeException e) {
        e.printStackTrace();
        return JsonResult.fail();
    }
}
