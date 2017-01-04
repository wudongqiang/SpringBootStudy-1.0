package com.nes.springboot.mapper;

import com.nes.springboot.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by wdq on 16-12-9.
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where id=#{id}")
    User getUserById(@Param("id") Long id);

    List<User> getUserAll();

}
