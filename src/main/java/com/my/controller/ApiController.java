package com.my.controller;

import com.my.annotation.InfoAnnotation;
import com.my.annotation.LimitAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/api/{id}")
    @InfoAnnotation(value = "信息",required = true)
    public String api(@PathVariable String id) {

        return "OK" + id;
    }

    /** 限流操作 **/
    @GetMapping("/limitApi/{id}")
    @LimitAnnotation(maxNumber = 20 ,second = 10)
    public String limitApi(@PathVariable String id) {
        System.out.println(id);
         return System.currentTimeMillis() + "";
    }

    @GetMapping("/t/{id}")
    public String t(@PathVariable String id) {
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(System.currentTimeMillis());
        redisTemplate.opsForValue().set(UUID.randomUUID().toString(), currentTimeMillis+"");
        return "OK";
    }
}
