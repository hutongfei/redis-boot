package com.my.annotation;

import java.lang.annotation.*;

/**
 * @author hutf
 * @createTime 2021年04月30日 21:56:00
 * 接口幂等操作注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Idempotent {

    String attachId() default "";
}
