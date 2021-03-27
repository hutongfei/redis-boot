package com.my;

import com.my.interceptor.CmtInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAspectJAutoProxy
public class RedisBootApplication {

    private final static Logger log = LoggerFactory.getLogger(RedisBootApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(RedisBootApplication.class, args);

        log.info("****************************************************************************************");
        log.info(" 抽奖活动  {}" ,"http://localhost:8080/draw");
        log.info(" 进度条 {} ","http://localhost:8080/processIndex");

        log.info(" 点赞功能 {} ","http://localhost:8080/setIndex");

        log.info("****************************************************************************************");

    }

//    public
}
