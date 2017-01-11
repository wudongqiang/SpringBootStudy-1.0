package com.nes.springboot.controller;

import com.nes.springboot.domain.Resource;
import com.nes.springboot.servcice.ResourceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wdq on 17-1-9.
 */
@RestController
@RequestMapping("/resource/db/")
public class ResourceDbController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/all")
    @ApiOperation("获取所有")
    public ResponseEntity<Resource> getResourceALL(){
        return new ResponseEntity(resourceService.findResourceAllDb(), HttpStatus.OK);
    }

    @ApiOperation("添加")
    @PutMapping("/add")
    public ResponseEntity addResource(@RequestBody List<Resource> resources){
        resourceService.addResourceDb(resources);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/get/{uuId}")
    public ResponseEntity<Resource> getResource(@RequestParam("uuId") String uuId){
        return new ResponseEntity(resourceService.getResourceDb(uuId),HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity updateResource(@RequestBody Resource resource){
        resourceService.updateResourceDb(resource);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/del/{uuId}")
    public ResponseEntity delResource(@RequestParam("uuId") String uuId){
        resourceService.deleteResourceDb(uuId);
        return new ResponseEntity(HttpStatus.OK);
    }


}
