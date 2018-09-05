package com.nes.springboot.study;

import com.nes.springboot.domain.User;
import com.nes.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Created by wdq on 16-11-9.
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private UserService userService;

    @GetMapping("index")
    public String index(){
        return "hello spring boot";
    }

    @GetMapping("/users")
    public Optional<User> users(@RequestParam Long t){
         return userService.getUserById(t);
    }

    @GetMapping("/all")
    public List<User> users(){
        return userService.getUserAlls();
    }
}
