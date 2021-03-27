package com.my.annotation;
import java.lang.annotation.*;

/**
 * @author hutf
 * @createTime 2020年10月03日 11:26:00
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InfoAnnotation {

     String value() default "";

     boolean required() default false;

}
