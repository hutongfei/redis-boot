package com.my.interceptor;

import com.my.annotation.InfoAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author hutf
 * @createTime 2020年10月03日 16:01:00
 */
@Configuration
public class CmtInterceptor implements HandlerInterceptor {

    private final static Logger log = LoggerFactory.getLogger(CmtInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("!!!!! CurrentThread is interceptor !!!");

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        InfoAnnotation methodAnnotation = handlerMethod.getMethodAnnotation(InfoAnnotation.class);

        if (methodAnnotation != null) {
            String value = methodAnnotation.value();
            boolean required = methodAnnotation.required();

            System.out.println(value + "          " + required);
        }
        String requestedSessionId = request.getRequestedSessionId();

        System.out.println(requestedSessionId);
//        Method method = handlerMethod.getMethod();
//        method.invoke(Object)

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}
