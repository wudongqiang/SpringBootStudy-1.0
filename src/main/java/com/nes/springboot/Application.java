package com.nes.springboot;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.nes.springboot.dubbo.provider.EmbeddedZooKeeper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * Created by wdq on 16-11-9.
 */
@EnableHystrix
@EnableDubbo
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // start embedded zookeeper server
        new EmbeddedZooKeeper(2181, false).start();

        SpringApplication.run(Application.class, args);
    }
}
