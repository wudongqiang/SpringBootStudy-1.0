package com.nes.springboot.dao;

import com.nes.springboot.domain.Resource;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by wdq on 17-1-10.
 */
public interface ResourceDao extends CrudRepository<Resource,String>{

    List<Resource> findAll();
}
