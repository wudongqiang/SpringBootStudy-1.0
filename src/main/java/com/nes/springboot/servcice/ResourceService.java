package com.nes.springboot.servcice;

import com.nes.springboot.domain.Resource;

import java.util.List;

/**
 * Created by wdq on 17-1-9.
 */
public interface ResourceService {

    List<Resource> findResourceAll();

    void addResource(Resource resource);

    Resource getResource(String uuId);

    void updateResource(Resource resource);

    void deleteResource(String uuId);

    List<Resource> findResourceAllDb();

    void addResourceDb(List<Resource> resource);

    Resource getResourceDb(String uuId);

    void updateResourceDb(Resource resource);

    void deleteResourceDb(String uuId);
}
