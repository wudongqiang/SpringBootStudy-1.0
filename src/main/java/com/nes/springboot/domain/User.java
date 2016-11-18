package com.nes.springboot.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wdq on 16-11-10.
 */
@ApiModel(value = "userModel")
public class User {

    @ApiModelProperty(name = "id",value = "用户id",dataType = "Long")
    private Long id;
    @ApiModelProperty(name = "name",value = "用户名称",dataType = "String")
    private String name;
    @ApiModelProperty(name = "age",value = "用户年龄",dataType = "Integer")
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
