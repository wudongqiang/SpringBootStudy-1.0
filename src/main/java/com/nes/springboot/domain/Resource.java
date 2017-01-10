package com.nes.springboot.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by wdq on 17-1-9.
 */
@Entity
@Table(name = "resource")
public class Resource {

    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "uuid")
    private String uuId;
    @ApiModelProperty(value = "url地址", example = "http://192.168.31.121", required = true)
    @Column(name = "url")
    private String url;
    @Column(name = "name")
    @ApiModelProperty(value = "名称", example = "本地测试地址")
    private String name;

    public Resource() {
    }

    public Resource(String uuId, String url, String name) {
        this.uuId = uuId;
        this.url = url;
        this.name = name;
    }

    public String getUuId() {
        return uuId;
    }

    public void setUuId(String uuId) {
        this.uuId = uuId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
