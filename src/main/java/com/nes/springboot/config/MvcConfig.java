package com.nes.springboot.config;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

//@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

        //SortHandlerMethodArgumentResolver sortResolver = ;
        // sortResolver.setSortParameter("xxxxx");

        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setPageParameterName("index");
        resolver.setSizeParameterName("size");

        resolver.setMaxPageSize(50);
        PageRequest pageable = new PageRequest(0, 50, new Sort(Sort.Direction.DESC, "lastModifiedTs"));

        resolver.setFallbackPageable(pageable);
        resolver.setOneIndexedParameters(true);
        argumentResolvers.add(resolver);
        super.addArgumentResolvers(argumentResolvers);
    }

}