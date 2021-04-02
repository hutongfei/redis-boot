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
public class PointController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/pointArticle")
    public String pointArticle(ModelMap modelMap) {
        String id = "2021";
        Object viewCount = redisTemplate.opsForValue().get(id);
        modelMap.put("count", viewCount);
//        model.addAttribute("id", id);
        return "/article";
    }

    @GetMapping("/incrPoint/{id}")
    @ResponseBody
    public Long incrPoint(@PathVariable String id) {
        Long increment = redisTemplate.opsForValue().increment(id, 1);
        return increment;
    }

}
