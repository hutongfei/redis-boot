package com.my.service;

import cn.hutool.core.img.Img;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author hutf
 * @createTime 2021年05月07日 21:20:00
 */
public class ParkDataDemo {

    public static void main(String[] args) throws IOException {
        String url3 = "https://search.jd.com/Search?keyword=%E7%94%B5%E8%84%91&enc=utf-8&suggest=1.his.0.0&wq=&pvid=ef494e87d49648579b254b71e5cb0bef";
        String url4 = "https://search.jd.com/Search?keyword=%E7%AC%94%E8%AE%B0%E6%9C%AC%E7%94%B5%E8%84%91&enc=utf-8&wq=%E7%AC%94%E8%AE%B0%E6%9C%ACd%27n&pvid=58cbcc10f82342eeaf1db2db1c77e755";
        String url5 = "https://list.jd.com/list.html?cat=670%2C671%2C672&go=0";



        Connection conn = Jsoup.connect(url4).timeout(5000).maxBodySize(0).followRedirects(false);
        //设置请求头,模拟浏览器登陆,绕过简单的反爬虫机制
        conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        conn.header("Accept-Encoding", "gzip, deflate, sdch");
        conn.header("Accept-Language", "zh-CN,zh;q=0.8");
        conn.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
        //从url中获取流并解析为Document对象
        Document doc = conn.get();

        Elements images = doc.getElementsByTag("img");
        System.out.println("**********************************************************");
        for (Element a : images) {
            String src = a.attr("src");
            if (StringUtils.isBlank(src)) {
                Attributes attributes = a.attributes();
//                data-lazy-img
//                data-img
                String dataLazyImg = a.attr("data-lazy-img");
                String dataImg = a.attr("data-img");

                src = a.attr("data-lazy-img");
                System.out.println(src);
            }
            if (!src.startsWith("http://")) {
                src = "http:" + src;
            }

        }





    }
}
