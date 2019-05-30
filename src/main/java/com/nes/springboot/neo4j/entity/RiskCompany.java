package com.nes.springboot.neo4j.entity;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/*
 * @NodeEntity：声明该类为Neo4j的节点类
 * @GraphId：Neo4j的主键id，必须为长整型
 * @Property：Neo4j的节点属性值，支持8种基本类型外加String
 */

@NodeEntity(label = "Company")
public class RiskCompany {

    @Property(name = "companyId")
    private String companyId;
    @Property(name = "esDate")
    private String esDate;
    @Property(name = "regCapCur")
    private String regCapCur;
    @Property(name = "companyName")
    private String companyName;
    @Property(name = "regCap")
    private String regCap;
    @Property(name = "status")
    private String status;
    @Property(name = "auditDate")
    private String auditDate;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getEsDate() {
        return esDate;
    }

    public void setEsDate(String esDate) {
        this.esDate = esDate;
    }

    public String getRegCapCur() {
        return regCapCur;
    }

    public void setRegCapCur(String regCapCur) {
        this.regCapCur = regCapCur;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegCap() {
        return regCap;
    }

    public void setRegCap(String regCap) {
        this.regCap = regCap;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
    }
}