package com.maoxian.backend.annotation;

import java.lang.annotation.*;

/**
 * @author Lin
 * @date 2023/10/12 14:51
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseNotIntercept {
    String value() default "";
}
