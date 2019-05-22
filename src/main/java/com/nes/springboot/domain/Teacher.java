package com.nes.springboot.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by wdq on 16-11-10.
 */
@ApiModel(value = "Teacher")
@Setter
@Getter
public class Teacher {

    @ApiModelProperty(name = "name",value = "教师姓名",dataType = "String")
    private String name;
    @ApiModelProperty(name = "sex",value = "教师sex",dataType = "String")
    private String sex;
//
//    public Teacher() {
//    }
//
//    public Teacher(String name, String sex) {
//        this.name = name;
//        this.sex = sex;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getSex() {
//        return sex;
//    }
//
//    public void setSex(String sex) {
//        this.sex = sex;
//    }
}
