package com.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author hutf
 * @createTime 2020年10月03日 11:25:00
 */
@Controller
@CrossOrigin
public class DrawController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/addDrawer/{name}")
    @ResponseBody
    public String addDrawer(@PathVariable String name) {
        Long drawer = redisTemplate.opsForSet().add("drawer", name);
        return "OK";
    }

    // 获取所有抽奖者
    @GetMapping("/getDrawer")
    @ResponseBody
    public Set<String> getDrawer() {
        Set drawer = redisTemplate.opsForSet().members("drawer");
        return drawer;
    }

    /** 抽检操作 **/
    @GetMapping("/drawer/{type}")
    @ResponseBody
    public Object drawer(@PathVariable String type) {
        Object drawer = redisTemplate.opsForSet().pop("drawer");
        return drawer;
    }

    @GetMapping("/draw")
    public String luckDraw() {
        return "/luckDraw";
    }


}
