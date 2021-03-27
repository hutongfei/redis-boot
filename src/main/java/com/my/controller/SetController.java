package com.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

/**
 * @author hutf
 * @createTime 2020年10月03日 11:25:00
 */
@Controller
@CrossOrigin
public class SetController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/setIndex/{id}")
    public String setIndex(@PathVariable String id) {
//        Object viewCount = redisTemplate.opsForValue().get(id);setIndex
//        model.addAttribute("count", viewCount);
//        model.addAttribute("id", id);
        return "/article";
    }

    @GetMapping("/upAdd/{id}")
    @ResponseBody
    public Long upAdd(@PathVariable String id) {
        Long increment = redisTemplate.opsForValue().increment(id, 1);
        return increment;
    }

}
