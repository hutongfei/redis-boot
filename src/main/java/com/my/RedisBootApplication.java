package com.my;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.UUID;

@SpringBootApplication
@EnableAspectJAutoProxy
//@EnableScheduling
public class RedisBootApplication {

    private final static Logger log = LoggerFactory.getLogger(RedisBootApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(RedisBootApplication.class, args);
        log.info("****************************************************************************************");
        log.info(" 抽奖活动  {}" ,"http://localhost:9000/draw");
        log.info("****************************************************************************************");
        log.info(" 物流进度条 {} ","http://localhost:9000/processIndex");
        log.info("****************************************************************************************");
        log.info(" 阅读量功能 {} ","http://localhost:9000/pointArticle");
        log.info("****************************************************************************************");
        log.info("排行榜 {}","http://localhost:9000/boardList");
        log.info("****************************************************************************************");
        log.info(" 接口限流操作 {} ","http://localhost:9000/limitApi/"+ UUID.randomUUID().toString());
        log.info("****************************************************************************************");
        log.info(" 商品详情页面，做静态缓存处理 {}","http://localhost:9000/1/details.html");
        log.info("****************************************************************************************");
        log.info("Redis分布式锁实现 {}", "http://localhost:9000/demo");
    }

//    public
}
