
package com.nes.springboot.neo4j.repository;

import com.nes.springboot.neo4j.entity.RiskCompany;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RiskCompanyRepository extends Neo4jRepository<RiskCompany,Long> {


    @Query("match p=(n:Company {companyName:{companyName}})-[:CompanyInvestToCompany|CompanyPartnerCompany|PersonInvestToCompany|PersonPosition|PersonLegal*0..2]-(removeCompany:Company)\n" +
            "        using index n:Company(companyName)\n" +
            "        where n <> removeCompany\n" +
            "        with collect(removeCompany)  as removeCompanies\n" +
            "        match p=(n:Company {companyName:{companyName}})-[r:CompanyInvestToCompany|CompanyPartnerCompany|PersonInvestToCompany|PersonPosition|PersonLegal*3..5]-(riskCompany:Company)-[riskRel:CompanyShixin|CompanyZhixing|CompanySszc]->()\n" +
            "        where not riskCompany in removeCompanies and n<>riskCompany \n" +
            "        and not riskCompany.companyName  =~ '.*资本.*' and  not riskCompany.companyName =~ '.*投资.*' and  not riskCompany.companyName =~ '.*创投.*'\n" +
            "        and  not riskCompany.companyName =~ '.*基金.*' and  not riskCompany.companyName =~ '.*资产管理.*'\n" +
            "        and all(a_n in nodes(p)[1..2]\n" +
            "            where a_n.companyName is null\n" +
            "                or  ( not a_n.companyName =~ '.*投资.*' and not a_n.companyName =~ '.*资本.*' and not a_n.companyName =~ '.*创投.*'\n" +
            "                and not a_n.companyName =~ '.*基金.*' and not a_n.companyName =~ '.*资产管理.*' )\n" +
            "        )\n" +
            "        return distinct riskCompany limit 100")
    List<Object> getRiskCompany(@Param("companyName") String companyName);
}
