package com.maoxian.gateway.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Web工具类
 *
 * @author Lin
 * @date 2023/9/21 15:30
 */
public class WebUtil {

    /**
     * 将字符串渲染到客户端
     *
     * @param response 响应
     * @param string   要渲染的字符串
     */
    public static void renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            throw new RuntimeException("字符串渲染错误");
        }
    }
}
