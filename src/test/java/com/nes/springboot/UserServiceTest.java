package com.nes.springboot;

import com.nes.springboot.service.HelloService;
import com.nes.springboot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by wdq on 16-11-18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
//@WebAppConfiguration
public class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    public void testAddUser(){
        userService.addUser("avva",12);
    }

    @Test
    public void testFind(){
        System.out.println(userService.getUserAlls());
    }

    @Test
    public void testGetUserbyId(){
        System.out.println(userService.getUserById(1L));
    }
}
