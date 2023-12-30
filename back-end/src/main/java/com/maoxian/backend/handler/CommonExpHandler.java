package com.maoxian.backend.handler;

import com.maoxian.backend.enums.HttpStatusEnum;
import com.maoxian.backend.exceprion.BusinessException;
import com.maoxian.backend.exceprion.RequestException;
import com.maoxian.backend.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 统一异常处理
 *
 * @author Lin
 * @date 2023/10/5 13:28
 */
@RestControllerAdvice
@Slf4j
public class CommonExpHandler {

    /**
     * 业务异常，提示错误信息
     *
     * @param e 错误
     * @return 响应类
     */
    @ExceptionHandler(BusinessException.class)
    public JsonResult handler(BusinessException e) {
        log.error(e.getMessage());
        return JsonResult.fail(e.getMessage());
    }

    /**
     * 请求参数异常
     *
     * @param e 错误
     * @return 响应类
     */
    @ExceptionHandler(RequestException.class)
    public JsonResult handler(RequestException e) {
        log.error(e.getMessage());
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
        log.error(e.getMessage());
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
        log.error(e.getMessage());
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
        log.error(e.getMessage());
        return JsonResult.fail(HttpStatusEnum.BAD_METHOD);
    }

    /**
     * 运行时异常，处理除认证授权外的异常
     *
     * @param e 错误
     * @return 响应类
     */
    @ExceptionHandler(RuntimeException.class)
    public JsonResult handler(RuntimeException e) {
        log.error(e.getMessage());
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
        log.error(e.getMessage());
        return JsonResult.fail(e.getMessage());
    }
}
