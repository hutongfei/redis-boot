package com.my.controller;

import com.my.annotation.LimitAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hutf
 * @createTime 2020年10月03日 11:25:00
 */
@Controller
@CrossOrigin
public class DemoController {

    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping("/demo")
    public String limitApi(ModelMap modelMap) {
        modelMap.put("msg", "hallo，word!");
        return "demo";
    }

}
