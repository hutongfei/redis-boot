package com.my.interceptor;

import com.my.annotation.LimitAnnotation;
import com.my.config.AspectConfig;
import com.my.utils.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;


/**
 * @author hutf
 * @createTime 2021年03月16日 10:08:00
 */
@Component
public class LimitInterceptor implements HandlerInterceptor {

    private final static Logger log = LoggerFactory.getLogger(LimitInterceptor.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(handler.toString());
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        LimitAnnotation annotation = method.getAnnotation(LimitAnnotation.class);

        if (annotation != null) {
            int maxNumber = annotation.maxNumber();
            long second = annotation.second();
            String ipAddress = IpUtils.getIpAddress(request);

            // 此处可用lua 脚本优化
            Long decrement = redisTemplate.opsForValue().increment(ipAddress,1);
            redisTemplate.expire(ipAddress, second, TimeUnit.SECONDS);

            if (decrement > maxNumber) {
                log.info("{}，**********被限流**********,{},s" , ipAddress , decrement );
                throw  new RuntimeException("频繁操作，30 s 后再试！");
            }

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {}
}
