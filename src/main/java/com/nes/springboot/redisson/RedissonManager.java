package com.nes.springboot.redisson;

import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.api.listener.MessageListener;
import org.redisson.api.listener.PatternMessageListener;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @author wdq
 * @date 2018-07-09-下午3:30
 */
public class RedissonManager {


    public static void main(String[] args) {
        subPattern();
    }

    /**
     * 精确key的订阅
     */
    private static void sub(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379")
                .setPassword("123456");

        RedissonClient client = Redisson.create(config);

        RTopic<Object> topic = client.getTopic("topic");
        topic.addListener(new MessageListener<Object>() {
            @Override
            public void onMessage(String channel, Object msg) {
                System.out.println(channel+"---"+msg);
            }
        });
        topic.publish("xxxxxxx");
    }

    /**
     * 模糊key的订阅
     */
    private static void subPattern(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379")
                .setPassword("123456");

        RedissonClient client = Redisson.create(config);
        //只要是topic.开头的都将订阅
        RPatternTopic<Object> topic = client.getPatternTopic("topic.*");
        topic.addListener(new PatternMessageListener<Object>() {
            @Override
            public void onMessage(String pattern, String channel, Object msg) {
                System.out.println(pattern+"===="+channel+"---"+msg);
            }
        });
        RTopic<Object> t = client.getTopic("topic.aaa");
        t.publish("this is pattern topic");
    }

    private static void setVal(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379")
                .setPassword("123456");

        RedissonClient client = Redisson.create(config);
        RBucket<String> bucket = client.getBucket("test_v");
        //如果test_v 不存在,会创建一个并设置1111
        bucket.set("1111",20,TimeUnit.SECONDS);
        client.shutdown();
    }

    private static void lockTest(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379")
                .setPassword("123456");

        RedissonClient client = Redisson.create(config);
        RLock lock = client.getLock("xxx");
        lock.lock(10, TimeUnit.SECONDS);

        System.out.println("xxxxxxxxxxxxxx");
        lock.unlock();
        client.shutdown();
    }
}
