package com.my.config;

import com.my.annotation.Idempotent;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author hutf
 * @createTime 2021年04月30日 21:59:00
 */
@Configuration
@Aspect
@Order(1)
public class IdempotentAspect {

    private final static Logger log = LoggerFactory.getLogger(AspectConfig.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @After(value = "pointCutMethod()")
    public void  afterMethod(JoinPoint point) {
        String attachId = this.getIdempotentAnnotation(point);
        if (StringUtils.isNotBlank(attachId)) {
//            redisTemplate.delete(attachId);
            log.info("后置置通知获取************attachId************ {}" ,attachId);
        }
    }

    @Before(value = "pointCutMethod()")
    public void beforeMethod(JoinPoint point) {
        String attachId = this.getIdempotentAnnotation(point);
        if (StringUtils.isNotBlank(attachId)) {
            Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(attachId, 1, 5, TimeUnit.SECONDS);// set 一个值
            if (!aBoolean){
                throw new RuntimeException("重复提交啦");
            }
            log.info("前置通知获取************attachId************ {}" ,attachId);
        }


    }

    @Around("pointCutMethod()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) {
        Object proceed = null;
        try {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            Idempotent declaredAnnotation = method.getDeclaredAnnotation(Idempotent.class);
            Idempotent annotation = method.getAnnotation(Idempotent.class);
            if (annotation != null) {
                String attachId = annotation.attachId();
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

     private String  getIdempotentAnnotation(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
         Idempotent annotation = method.getAnnotation(Idempotent.class);
         if (annotation != null) {
             HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
             return method.getName() + request.getSession().getId();
         }
        return null;
    }


    @Pointcut(value = "@annotation(com.my.annotation.Idempotent)")
    public void  pointCutMethod() {}

}
