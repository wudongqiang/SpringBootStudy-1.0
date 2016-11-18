package com.nes.springboot.service;

import org.springframework.stereotype.Service;

/**
 * Created by wdq on 16-11-9.
 */
@Service
public class HelloServiceImpl implements HelloService{

    @Override
    public String show() {
        System.out.println("call show.......");
        return "this is show method...";
    }

}
