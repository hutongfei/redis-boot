package com.my.config;

import com.my.annotation.LimitAnnotation;
import com.my.constant.CommonConstant;
import com.my.utils.IpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author hutf
 * @createTime 2020年10月03日 11:36:00
 */
@Configuration
@Aspect
public class AspectConfig {

    private final static Logger log = LoggerFactory.getLogger(AspectConfig.class);

    @Autowired
    private RedisTemplate redisTemplate;


    @After(value = "pointCutMethod()")
    public void  afterMethod(JoinPoint point) {
        if (!this.doRequestLimit(point)) {
            throw  new RuntimeException("频繁操作，30 s 后再试！");
        }

    }

    private boolean doRequestLimit(JoinPoint point) {
        // 获取唯一的ip Key
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ipAddress = IpUtils.getIpAddress(request);

        // 获取注解信息
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        LimitAnnotation annotation = method.getAnnotation(LimitAnnotation.class);

        int maxNumber = annotation.maxNumber();
        long second = annotation.second();

        Long increment = redisTemplate.opsForValue().increment(CommonConstant.IP_LIMIT + ipAddress);// 做加1 操作
        redisTemplate.expire(CommonConstant.IP_LIMIT + ipAddress, second, TimeUnit.SECONDS);// 设置过期时间

        if (increment > maxNumber){
            log.info("{}，**********被限流**********,{}" , ipAddress  );
            return false;
        }

        return true;

    }

    @Before(value = "pointCutMethod()")
    public void beforeMethod(JoinPoint point) {
    }

    @Around("pointCutMethod()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) {
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }finally {
            System.out.println(" end aroundMethod ");
        }

        return null;
    }

    @Pointcut(value = "@annotation(com.my.annotation.LimitAnnotation)")
    public void  pointCutMethod() {}

}
