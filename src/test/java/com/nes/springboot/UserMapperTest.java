package com.nes.springboot;

import com.nes.springboot.domain.User;
import com.nes.springboot.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wdq on 16-12-9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testGetUserById(){
        User u = userMapper.getUserById(1L);
        System.out.println(u);
    }

    @Test
    public void testGetUserAll(){
        System.out.println(userMapper.getUserAll());
    }
}
