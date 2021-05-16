package com.my.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @author hutf
 * @createTime 2021年05月16日 16:25:00
 *  redis key过期回调 配置
 */
@Component
public class RedisKeyExpireCallConfig extends KeyExpirationEventMessageListener {

    @Autowired
    private RedisTemplate redisTemplate;

    public RedisKeyExpireCallConfig(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        byte[] body = message.getBody();
        System.out.println("回调了回调了回调了回调了回调了回调了回调了回调了回调了回调了回调了");
        // 此处为过期的 key 值
        String key = new String(body);


        super.onMessage(message, pattern);
    }
}
