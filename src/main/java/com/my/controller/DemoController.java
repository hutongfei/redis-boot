package com.my.controller;

import com.my.annotation.LimitAnnotation;
import com.my.entity.Pro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * @author hutf
 * @createTime 2020年10月03日 11:25:00
 */
@Controller
@CrossOrigin
public class DemoController {

    static int counter = 0;

    @Autowired
    private RedisTemplate redisTemplate;

    static CountDownLatch cdl = new CountDownLatch(100);


    @GetMapping("/demo")
    @ResponseBody
    public String demo() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    redisTemplate.opsForValue().setIfAbsent("redisLock", "1");
                    for (int j = 0; j < 100; j++) {
                        counter++;       //结果为 100*100 = 10000
                    }
                    cdl.countDown();
                    redisTemplate.delete("redisLock");
                }
            }).start();
        }

        cdl.await();
        cdl = new CountDownLatch(100);
        return counter+"";
    }

}
