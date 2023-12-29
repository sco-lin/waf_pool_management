package com.maoxian.gateway.handler;

import com.alibaba.fastjson.JSON;
import com.maoxian.gateway.annotation.ResponseNotIntercept;
import com.maoxian.gateway.util.JsonResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * 全局响应处理器
 *
 * @author Lin
 * @date 2023/10/12 11:47
 */
@RestControllerAdvice
public class ResponseHandler implements ResponseBodyAdvice<Object> {

    /**
     * 是否应用响应处理
     *
     * @param returnType    控制器方法的返回类型
     * @param converterType 处理响应的消息转换器类型
     * @return 是否进行响应处理
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {

        //类上或者方法上有@ResponseNotIntercept注解，返回false
        return !returnType.getDeclaringClass().isAnnotationPresent(ResponseNotIntercept.class)
                && !Objects.requireNonNull(returnType.getMethod()).isAnnotationPresent(ResponseNotIntercept.class);
    }

    /**
     * 实际的响应处理，在响应发送之前被调用
     *
     * @param body                  控制器方法返回的响应体
     * @param returnType            控制器方法的返回类型
     * @param selectedContentType   所选的的响应内容类型
     * @param selectedConverterType 处理响应的消息转换器类型
     * @param request               http请求
     * @param response              http响应
     * @return 处理后的body
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        //如果body已经被包装过，就不用包装了
        if (body instanceof JsonResult) {
            return body;
        }
        if (body instanceof String) {
            //解决返回值是字符串时不能正常包装的问题
            return JSON.toJSONString(JsonResult.success(body));
        }
        return JsonResult.success(body);
    }
}
