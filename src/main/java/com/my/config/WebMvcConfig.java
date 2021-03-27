package com.my.config;

import com.my.interceptor.CmtInterceptor;
import com.my.interceptor.LimitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hutf
 * @createTime 2020年10月03日 16:08:00
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LimitInterceptor limitInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new CmtInterceptor()).addPathPatterns("/**").excludePathPatterns("/limitApi/*","/limitApi");

        registry.addInterceptor(limitInterceptor).addPathPatterns("/limitApi/*","/limitApi");
        registry.addInterceptor(new PathInterceptor()).addPathPatterns("/**");

    }
}
