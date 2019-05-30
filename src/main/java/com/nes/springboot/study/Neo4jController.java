package com.nes.springboot.study;

import com.nes.springboot.neo4j.entity.RiskCompany;
import com.nes.springboot.neo4j.repository.RiskCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wdq on 16-11-10.
 */
@RestController
public class Neo4jController {

    @Autowired
    private RiskCompanyRepository riskCompanyRepository;

    @RequestMapping("/neo4j")
    public Object index(@RequestParam String companyName){
        return riskCompanyRepository.getRiskCompany(companyName);

    }
}
