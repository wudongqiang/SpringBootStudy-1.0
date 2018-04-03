package com.nes.springboot.study;

import com.nes.springboot.domain.User;
import com.nes.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wdq on 16-11-9.
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private UserService userService;

    @RequestMapping("index")
    public String index(){
        return "hello spring boot";
    }

    @RequestMapping("/users")
    public List<User> users(@RequestParam Integer t){
         return userService.getUserAlls();
    }
}
