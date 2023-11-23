package com.maoxian.handler;

import com.maoxian.enums.HttpStatusEnum;
import com.maoxian.exceprion.BusinessExp;
import com.maoxian.exceprion.RequestExp;
import com.maoxian.vo.JsonResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 统一异常处理
 */
@RestControllerAdvice
public class CommonExpHandler {

    /**
     * 业务异常，提示错误信息
     *
     * @param e 错误
     * @return 响应类
     */
    @ExceptionHandler(BusinessExp.class)
    public JsonResult handler(BusinessExp e) {
        e.printStackTrace();
        return JsonResult.fail(e.getMessage());
    }

    /**
     * 请求参数异常
     *
     * @param e 错误
     * @return 响应类
     */
    @ExceptionHandler(RequestExp.class)
    public JsonResult handler(RequestExp e) {
        e.printStackTrace();
        return JsonResult.fail(HttpStatusEnum.BAD_REQUEST);
    }

    /**
     * 认证异常，处理认证失败
     *
     * @param e 错误
     * @return 响应类
     */
    @ExceptionHandler(AuthenticationException.class)
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
    public JsonResult handler(AccessDeniedException e) {
        e.printStackTrace();
        return JsonResult.fail(HttpStatus.FORBIDDEN.value(), e.getMessage());
    }

    /**
     * 请求方式不支持异常
     *
     * @param e 异常
     * @return 响应类
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JsonResult handler(HttpRequestMethodNotSupportedException e) {
        e.printStackTrace();
        return JsonResult.fail(e.getMessage());
    }

    /**
     * 运行时异常，处理除认证授权外的异常
     *
     * @param e 错误
     * @return 响应类
     */
    @ExceptionHandler(RuntimeException.class)
    public JsonResult handler(RuntimeException e) {
        e.printStackTrace();
        return JsonResult.fail();
    }

    /**
     * 处理4xx异常
     *
     * @param e 异常
     * @return 响应类
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public JsonResult handler(NoHandlerFoundException e) {
        e.printStackTrace();
        return JsonResult.fail(e.getMessage());
    }
}
