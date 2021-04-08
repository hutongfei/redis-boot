package com.my.controller;

import com.my.annotation.LimitAnnotation;
import com.my.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author hutf
 * @createTime 2020年10月03日 11:25:00
 */
@RestController
@CrossOrigin
public class PageController {


    @Autowired
    private PageService pageService;

    @RequestMapping(value="/{id}/details.html", produces="text/html")
    public String details(@PathVariable String id, Model model) throws Exception {
        return pageService.ftlToHtml(id);
    }

}
