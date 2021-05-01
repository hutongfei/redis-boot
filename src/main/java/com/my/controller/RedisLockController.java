package com.my.controller;

import com.my.config.RedissionConfig;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author hutf
 * @createTime 2020年10月03日 11:25:00
 */
@Controller
@CrossOrigin
public class RedisLockController {

    static int counter = 0;

    @Autowired
    private RedisTemplate redisTemplate;

    static CountDownLatch cdl = new CountDownLatch(100);

    @Autowired
    private RedissonClient redissonClient;


    @GetMapping("/redisLock")
    @ResponseBody
    public String redisLock() throws InterruptedException {
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

    @GetMapping("/redissionLock")
    @ResponseBody
    public String redissionLock() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    RLock rLock = redissonClient.getLock("rLock");
                    rLock.lock(20, TimeUnit.SECONDS);
                    for (int j = 0; j < 100; j++) {
                        counter++;       //结果为 100*100 = 10000
                    }
                    cdl.countDown();
                    rLock.unlock();
                }
            }).start();
        }

        cdl.await();
        cdl = new CountDownLatch(100);
        return counter+"";
    }

}
