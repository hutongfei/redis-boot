package com.my.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.my.entity.Pro;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hutf
 * @createTime 2021年04月09日 23:52:00
 */
@Component
public class parkDataService {

    static Object object = new Object();

    @Autowired
    private RedisTemplate redisTemplate;

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

//    @Scheduled(fixedDelay = 20000, initialDelay = 5000)
    public void parkMethod() throws IOException, InterruptedException {
        //使用JSOUP获取连接
        Connection conn = Jsoup.connect("https://list.jd.com/list.html?cat=670%2C671%2C672&go=0").timeout(5000);
        //设置请求头,模拟浏览器登陆,绕过简单的反爬虫机制
        conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        conn.header("Accept-Encoding", "gzip, deflate, sdch");
        conn.header("Accept-Language", "zh-CN,zh;q=0.8");
        conn.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
        //从url中获取流并解析为Document对象
        Document doc = conn.get();

        Elements elementsByClass = doc.getElementsByClass("gl-i-wrap");


        for (int i = 0; i < elementsByClass.size(); i++) {

            Element element = elementsByClass.get(i);

            Elements elements2 = element.getElementsByClass("p-img");
            String title = elements2.get(0).getElementsByTag("a").attr("title");

            Element elements3 = element.getElementsByClass("p-price").get(0).getElementsByTag("strong").get(0);
            String price = elements3.text();

            Elements elements4 = element.getElementsByClass("p-shop");
            Elements a = elements4.get(0).getElementsByTag("a");
            String boss = a.text();


            Element nameElement = element.getElementsByClass("p-name p-name-type-3").get(0);
            Element name = nameElement.getElementsByTag("a").get(0);

            Pro pro = new Pro();
            pro.setName(name.text());
            pro.setPrice(price);
            pro.setShopName(boss);
            pro.setTitle(title);

            Thread.sleep(1000);
            synchronized (object){
                pro.setId(atomicInteger.incrementAndGet());
                redisTemplate.opsForValue().set(pro.getId(), pro.toString());
            }



            System.out.println(pro.toString());
        }

//        for (    Element element : elementsByClass) {
//
//        }
    }
}
