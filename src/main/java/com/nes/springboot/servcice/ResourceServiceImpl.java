package com.nes.springboot.servcice;

import com.google.gson.*;
import com.nes.springboot.domain.Resource;
import com.nes.springboot.util.FileUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by wdq on 17-1-9.
 */
@Service
public class ResourceServiceImpl implements ResourceService {


    private List<Resource> jsonArrayToList(String content){
        List<Resource> resources = new ArrayList<>();
        Gson gson = new Gson();
        //创建一个JsonParser
        JsonParser parser = new JsonParser();
        //通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
        JsonElement el = parser.parse(content);

//        //把JsonElement对象转换成JsonObject
//        JsonObject jsonObj = null;
//        if(el.isJsonObject()){
//            jsonObj = el.getAsJsonObject();
//        }
        //把JsonElement对象转换成JsonArray
        JsonArray jsonArray = null;
        if(el.isJsonArray()){
            jsonArray = el.getAsJsonArray();
        }

        //遍历JsonArray对象
        Iterator it = jsonArray.iterator();
        while(it.hasNext()){
            JsonElement e = (JsonElement)it.next();
            //JsonElement转换为JavaBean对象
            resources.add(gson.fromJson(e, Resource.class));
        }
        return resources;
    }

    @Override
    public List<Resource> findResourceAll() {
        return jsonArrayToList(FileUtils.readFile());
    }

    @Override
    public void addResource(Resource resource) {
        resource.setUuId(UUID.randomUUID().toString());
        List<Resource> resources = findResourceAll();
        resources.add(resource);
        FileUtils.writeFile(new Gson().toJson(resources));
    }

    @Override
    public Resource getResource(String uuId){
        List<Resource> resources = findResourceAll();
        for(Resource resource : resources){
            if(uuId.equals(resource.getUuId())){
                return resource;
            }
        }
        return null;
    }

    @Override
    public void updateResource(Resource resource){
        List<Resource> resources = findResourceAll();
        for(Resource res : resources){
            if(res.getUuId().equals(resource.getUuId())){
                res.setName(resource.getName());
                res.setUrl(resource.getUrl());
            }
        }
        FileUtils.writeFile(new Gson().toJson(resources));
    }
}
