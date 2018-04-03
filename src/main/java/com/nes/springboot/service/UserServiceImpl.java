package com.nes.springboot.service;

import com.nes.springboot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by wdq on 16-11-9.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addUser(String name, Integer age) {
        return jdbcTemplate.update("insert into `user` (age,name) VALUES (?,?)",age,name);
    }

    @Override
    public void delUserByName(String name) {
        jdbcTemplate.update("delete from user where NAME =?",name);
    }

    @Override
    public List<User> getUserAlls() {
        int i=12/0;
        return jdbcTemplate.query("select id,age,name from user ;", new User());
    }

    @Override
    public User getUserById(Long id) {
        return jdbcTemplate.queryForObject("select id,age,name from user where id=?", new Object[]{id},new User());
    }
}
