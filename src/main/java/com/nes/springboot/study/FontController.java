package com.nes.springboot.study;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wdq on 16-11-10.
 */
@Controller
public class FontController {

    @RequestMapping("/")
    public String index(ModelMap map){
        map.put("show","this is template demo");
        return "index";

    }
}
