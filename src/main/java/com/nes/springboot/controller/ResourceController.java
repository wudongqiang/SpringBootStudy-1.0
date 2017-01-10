package com.nes.springboot.controller;

import com.nes.springboot.domain.Resource;
import com.nes.springboot.servcice.ResourceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wdq on 17-1-9.
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/all")
    @ApiOperation("获取所有")
    public ResponseEntity<Resource> getResourceALL(){
        return new ResponseEntity(resourceService.findResourceAll(), HttpStatus.OK);
    }

    @ApiOperation("添加")
    @PutMapping("/add")
    public ResponseEntity addResource(@RequestBody Resource resource){
        resourceService.addResource(resource);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/get/{uuId}")
    public ResponseEntity<Resource> addResource(@RequestParam("uuId") String uuId){
        return new ResponseEntity(resourceService.getResource(uuId),HttpStatus.OK);
    }

    @PostMapping("/update/{uuId}")
    public ResponseEntity updateResource(@RequestBody Resource resource){
        resourceService.updateResource(resource);
        return new ResponseEntity(HttpStatus.OK);
    }

}
