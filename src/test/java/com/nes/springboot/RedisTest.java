package com.nes.springboot;

import com.nes.springboot.redis.RedisLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author wdq
 * @date 2018-07-05-上午10:52
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

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
        } finally {
            lock.unlock();
        }
    }

    private void setSync(String key) {
        if (redisTemplate.hasKey(key)) {

            ValueOperations valueOperations = redisTemplate.opsForValue();
            int v = Integer.parseInt(valueOperations.get(key).toString());
            System.out.println(Thread.currentThread().getName() + "--->" + v);
//            valueOperations.set(key, --v);
            //必须是 StringRedisSerializer
            valueOperations.increment(key, 1);

            System.out.println(valueOperations.get(key));

        } else {
            System.out.println("key不存在");
        }
    }


    @Test
    public void testModifyRedisKey() {
        String key = "t_modify";
        for (int i = 0; i < 10; i++) {
            String v = i + "_v";
            new Thread(() -> {
                modifyRedisKey(key, v);
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        try{
            Thread.sleep(2100);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void modifyRedisKey(String key, String value) {
        Boolean a = redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            StringRedisSerializer serializer = new StringRedisSerializer();
            Boolean aBoolean = connection.setNX(serializer.serialize(key), serializer.serialize(value));
            connection.close();
            return aBoolean;
        });

        String s = redisTemplate.opsForValue().get(key);
        System.out.println("当前值:" + s + ", flga=" + a);
    }
}
