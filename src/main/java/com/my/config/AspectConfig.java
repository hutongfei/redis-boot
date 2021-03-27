package com.my.config;

import com.my.annotation.InfoAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;

/**
 * @author hutf
 * @createTime 2020年10月03日 11:36:00
 */
@Configuration
@Aspect
public class AspectConfig {

    private final static Logger log = LoggerFactory.getLogger(AspectConfig.class);

    @After(value = "pointCutMethod()")
    public void  afterMethod() {}

    @Before(value = "pointCutMethod()")
    public void beforeMethod(JoinPoint point) {
//        redisTemplate.opsForValue().set();
    }

    @Around("pointCutMethod()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) {
        Object proceed = null;
        try {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            InfoAnnotation annotation = method.getAnnotation(InfoAnnotation.class);
            if (annotation != null) {
                Class<? extends InfoAnnotation> aClass = annotation.getClass();
                boolean required = annotation.required();
                String value = annotation.value();

                System.out.println(value +  "  " + required);
            }

            proceed = joinPoint.proceed();
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }finally {
            System.out.println(" end aroundMethod ");
        }

        return null;
    }


    @Pointcut(value = "@annotation(com.my.annotation.InfoAnnotation)")
    public void  pointCutMethod() {}
}
