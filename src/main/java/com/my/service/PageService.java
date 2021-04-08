package com.my.service;

import com.my.entity.Product;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.util.*;

/**
 * @author hutf
 * @createTime 2021年04月08日 22:26:00
 */
@Service
public class PageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageService.class) ;
    private static final String PATH = "/templates/" ;

    private static Map<String, Product> item = null;

    static {

        item = new HashMap<>();

        Product product = new Product("1", "华为手机",8000.00,"不可描述");
        item.put("1", product);

        product = new Product("2", "联想电脑",13000.00,"联想品牌你值得拥有");
        item.put("2", product);

        product = new Product("3", "苹果手机",7900.00,"哇，苹果哦");
        item.put("3", product);

        product = new Product("4", "小米",4000.00,"雷军代言哦");
        item.put("4", product);

    }

    @Autowired
    private RedisTemplate redisTemplate;

    public String ftlToHtml(String id) throws Exception {

        Object o = redisTemplate.opsForValue().get("page:"+id);
        if (o != null){
            LOGGER.info("************************************整个页面数据html获取*************************走Redis缓存**********************************");
            return o.toString();
        }

        // 创建配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
        // 设置模板路径
        String classpath = this.getClass().getResource("/").getPath();
        configuration.setDirectoryForTemplateLoading(new File(classpath + PATH));
        // 加载模板
        Template template = configuration.getTemplate("details.ftl");
        // 数据模型
        Map<String, Object> map = new HashMap<>();
        map.put("title", "ID为 " + id + " 的商品详情页面");
        map.put("product",getMap(id)) ;
        // 静态化页面内容
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        LOGGER.info("content:{}",content);
//        InputStream inputStream = IOUtils.toInputStream(content,"UTF-8");
//        // 输出文件
//        FileOutputStream fileOutputStream = new FileOutputStream(new File("F:/page/newPage.html"));
//        IOUtils.copy(inputStream, fileOutputStream);
//        // 关闭流
//        inputStream.close();
//        fileOutputStream.close();
        redisTemplate.opsForValue().set("page:"+id, content);
        return content;
    }

    private Product getMap (String id){
        LOGGER.info("************************页面数据获取*******************************走数据库**********************");
        return item.get(id);
    }
}
