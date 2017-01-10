package com.nes.springboot.domain;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by wdq on 17-1-9.
 */
public class Resource {

    @ApiModelProperty(value = "uuid")
    private String uuId;
    @ApiModelProperty(value = "url地址", example = "http://192.168.31.121", required = true)
    private String url;
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
