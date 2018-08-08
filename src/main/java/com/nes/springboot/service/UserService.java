package com.nes.springboot.service;

import com.nes.springboot.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by wdq on 16-11-9.
 */
public interface UserService {

    int addUser(String name, Integer age);

    void delUserByName(String name);

    List<User> getUserAlls();

    Optional<User> getUserById(Long id);



}
