package com.my.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hutf
 * @createTime 2021年05月01日 12:36:00
 */
@Configuration
public class RedissionConfig {

    @Value("${redission.address}")
    private String redissionAddress;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();

        config.useSingleServer()
                .setAddress(redissionAddress);

        config.useSingleServer().setDatabase(0);
        return Redisson.create(config);
    }

}
