package com.nes.springboot.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * Created by wdq on 16-11-9.
 */
@Service(version = "1.0.0")
public class HelloServiceImpl implements HelloService{

    @Override
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000") })
    public String show(String name) {
        System.out.println("call show.......");
//        return "this is show method...";
        throw new RuntimeException("Exception to show hystrix enabled.");
    }

}
