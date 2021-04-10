package com.my;

import com.my.interceptor.CmtInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.UUID;

@SpringBootApplication
@EnableAspectJAutoProxy
//@EnableScheduling
public class RedisBootApplication {

    private final static Logger log = LoggerFactory.getLogger(RedisBootApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(RedisBootApplication.class, args);
        log.info("****************************************************************************************");
        log.info(" 抽奖活动  {}" ,"http://localhost:8080/draw");
        log.info("****************************************************************************************");
        log.info(" 物流进度条 {} ","http://localhost:8080/processIndex");
        log.info("****************************************************************************************");
        log.info(" 阅读量功能 {} ","http://localhost:8080/pointArticle");
        log.info("****************************************************************************************");
        log.info("排行榜 {}","http://localhost:8080/boardList");
        log.info("****************************************************************************************");
        log.info(" 接口限流操作 {} ","http://localhost:8080/limitApi/"+ UUID.randomUUID().toString());
        log.info("****************************************************************************************");
        log.info(" 商品详情页面，做静态缓存处理 {}","http://localhost:8080/1/details.html");

    }

//    public
}
