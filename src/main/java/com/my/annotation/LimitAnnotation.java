package com.my.annotation;

import java.lang.annotation.*;

/**
 * @author hutf
 * @createTime 2021年03月16日 10:01:00
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LimitAnnotation {

    String value() default "";
    // 最大的请求数
    int maxNumber() default 10;
    // 时区 单位秒
    int second() default 30;
}
