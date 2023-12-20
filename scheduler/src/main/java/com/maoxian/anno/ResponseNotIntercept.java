package com.maoxian.anno;

import java.lang.annotation.*;

/**
 * @author Lin
 * @date 2023/12/18 23:35
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseNotIntercept {
    String value() default "";
}
