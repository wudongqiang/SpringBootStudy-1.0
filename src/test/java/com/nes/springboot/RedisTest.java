package com.nes.springboot;

import com.nes.springboot.redis.RedisLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author wdq
 * @date 2018-07-05-上午10:52
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisTest {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

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

    private  void set(String key) {
        RedisLock lock = new RedisLock(redisTemplate, key, 10000, 2000);
        try {
            if (lock.lock()) {
                ValueOperations valueOperations = redisTemplate.opsForValue();
                int v = Integer.parseInt(valueOperations.get(key).toString()) - 1;
                System.out.println(Thread.currentThread().getName() + "--->" + v);
                valueOperations.set(key, v);
            } else {
                System.out.println("key不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    private void setSync(String key) {
        if (redisTemplate.hasKey(key)) {

//            redisTemplate.watch(key);
//            redisTemplate.multi();

            ValueOperations valueOperations = redisTemplate.opsForValue();
            int v = Integer.parseInt(valueOperations.get(key).toString());
            System.out.println(Thread.currentThread().getName() + "--->" + v);
//            valueOperations.set(key, --v);

            valueOperations.increment(key,1);

            System.out.println(valueOperations.get(key));
//            List e = redisTemplate.exec();
//            System.out.println("==>"+e);

        } else {
            System.out.println("key不存在");
        }

    }
}
