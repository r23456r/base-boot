package com.bc.redis.service;

import com.bc.redis.service.impl.RedisMessageServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    @ConditionalOnProperty(
            prefix = "message",
            value = "server-type",
            havingValue = "redis")
    @Bean
    MessageService redisMessageService() {
        return new RedisMessageServiceImpl();
    }

}
