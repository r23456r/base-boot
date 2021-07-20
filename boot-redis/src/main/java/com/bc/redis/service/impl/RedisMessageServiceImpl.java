package com.bc.redis.service.impl;

import com.alibaba.fastjson.JSON;
import com.bc.redis.service.MessageService;
import com.bc.utils.RedisUtils;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.util.Map;

import static com.bc.constant.CommonConstant.LATCH;


/**
 * redis消息队列机制
 * @author daibing
 */
public class RedisMessageServiceImpl implements MessageService {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private RedissonClient redissonClient;

    @Override
    @Async("taskExecutor")
    public void publishMessage(String message) {
        Map map = JSON.parseObject(message, Map.class);
        String keyId = (String) map.get("keyId");
        RCountDownLatch latch = redissonClient.getCountDownLatch(LATCH + keyId);
        latch.trySetCount(1);
        redisUtils.publish("kms", message);
    }
}
