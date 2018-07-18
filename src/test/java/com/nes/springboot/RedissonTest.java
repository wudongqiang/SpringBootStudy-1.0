package com.nes.springboot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author wdq
 * @date 2018-07-05-上午10:52
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class RedissonTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private Redisson redisson;

    @Before
    public void before() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379")
                .setPassword("123456");
        redisson = (Redisson) Redisson.create(config);
    }

    @Test
    public void testSetV() {
        String key = "tttt-0";
        redisTemplate.opsForValue().set(key, "10");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                set(key);
            }).start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(redisTemplate.opsForValue().get(key));
        redisTemplate.delete(key);
    }

    private void set(String key) {
        RLock lock = redisson.getLock(key+"_key");
        try {
            lock.lock(10, TimeUnit.SECONDS);

            ValueOperations valueOperations = redisTemplate.opsForValue();
            int v = Integer.parseInt(valueOperations.get(key).toString()) - 1;
            System.out.println(Thread.currentThread().getName() + "--->" + v);
            valueOperations.set(key, v+"");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
