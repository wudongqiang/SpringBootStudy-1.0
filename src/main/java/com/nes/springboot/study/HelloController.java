package com.nes.springboot.study;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wdq on 16-11-9.
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("index")
    public String index(){
        return "hello spring boot";
    }
}
