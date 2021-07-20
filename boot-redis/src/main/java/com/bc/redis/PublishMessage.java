package com.bc.redis;

import com.alibaba.fastjson.JSON;
import com.bc.utils.RedisUtils;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.bc.constant.CommonConstant.LATCH;

/**
 * 发布消息
 */
@Service
public class PublishMessage {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 消息发送
     *
     * @param message
     */
    @Async("taskExecutor")
    public void publish(String message) {
        Map map = JSON.parseObject(message, Map.class);
        String keyId = (String) map.get("keyId");
        RCountDownLatch latch = redissonClient.getCountDownLatch(LATCH + keyId);
        latch.trySetCount(1);
        redisUtils.publish("kms", message);
    }
}
