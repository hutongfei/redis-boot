package com.my.controller;

import com.my.annotation.Idempotent;
import com.my.annotation.LimitAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author hutf
 * @createTime 2020年10月03日 11:25:00
 */
@RestController
@CrossOrigin
public class ApiController {

    @Autowired
    private RedisTemplate redisTemplate;


    /** 限流操作 **/
    @GetMapping("/limitApi/{id}")
    @LimitAnnotation(maxNumber = 20 ,second = 10)
    public String limitApi(@PathVariable String id) {
        System.out.println(id);// 幂等 idempotent
         return UUID.randomUUID().toString()+ "";
    }

    @GetMapping("/idempotent/{id}")
    @Idempotent
    public String idempotent(@PathVariable String id, HttpServletRequest request) throws InterruptedException {
        System.out.println(id);// 幂等 idempotent
        Thread.sleep(2000);
        return UUID.randomUUID().toString()+ " 幂等" ;
    }

}
