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

import java.util.*;

/**
 * @author hutf
 * @createTime 2021年03月26日 16:30:00
 */
@Controller
@CrossOrigin
public class ZSortController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/process/{id}")
    @ResponseBody
    public List<Object> process(@PathVariable String id) {
        Set range = redisTemplate.opsForZSet().reverseRangeWithScores(id, 0, -1);
        Iterator iterator = range.iterator();
        List<Object> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            list.add(next);
        }
        return list;
    }

    @GetMapping("/processAdd/{id}/{desc}")
    @ResponseBody
    public Boolean processAdd(@PathVariable String id, @PathVariable String desc) {
        return redisTemplate.opsForZSet().add(id, desc, System.currentTimeMillis());
    }

    @GetMapping("/processIndex")
    public String processIndex(Model model) {
        model.addAttribute("id", "1002");
        return "progressBar";
    }

    @GetMapping("/boardList")
    public String boardList(ModelMap modelMap) {
        modelMap.put("tianmao", "天猫商城销量排行榜");
        Set range = redisTemplate.opsForZSet().reverseRangeWithScores("tianmao", 0, -1);
        Iterator iterator = range.iterator();
        List<Object> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            list.add(next);
        }

        modelMap.put("list", list);
        return "leaderBoard";
    }

    @GetMapping("addCount/{id}")
    @ResponseBody
    public List<Object> addCount(@PathVariable String id) {
        redisTemplate.opsForZSet().incrementScore("tianmao", id, 1);

        Set range = redisTemplate.opsForZSet().reverseRangeWithScores("tianmao", 0, -1);
        Iterator iterator = range.iterator();
        List<Object> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            list.add(next);
        }

        return list;

    }

    @GetMapping("addItem/{id}")
    @ResponseBody
    public  List<Object> addItem(@PathVariable String id) {
        redisTemplate.opsForZSet().add("tianmao", id, 0);

        Set range = redisTemplate.opsForZSet().reverseRangeWithScores("tianmao", 0, -1);
        Iterator iterator = range.iterator();
        List<Object> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            list.add(next);
        }

        return list;

    }

}
