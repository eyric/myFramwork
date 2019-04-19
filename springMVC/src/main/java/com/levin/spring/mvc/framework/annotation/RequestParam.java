package com.levin.spring.mvc.framework.annotation;

import java.lang.annotation.*;

/**
 * @ Author: levin
 * @ date: 2018/2/25 上午10:21
 * @ description:
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {
    String value() default "";

    boolean required() default true;
}
