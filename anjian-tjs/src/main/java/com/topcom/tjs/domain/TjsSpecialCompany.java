package com.topcom.tjs.domain;

import com.topcom.cms.base.model.BaseEntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
@Entity
@Table(name = "tjs_special_company")
public class TjsSpecialCompany extends BaseEntityModel {

    @Column(columnDefinition="COMMENT '单位名称'")
    private String companyName;

    @Column(columnDefinition="COMMENT '统一社会信用代码'")
    private String SCC;

    @Column(columnDefinition="COMMENT '注册地址'")
    private String address;

    @Column(columnDefinition="COMMENT '列入安全生产监管重点企业'")
    private Boolean special;

    @Column(columnDefinition="COMMENT '规模以上生产经营单位'")
    private Boolean scale;

    @Column(columnDefinition="COMMENT '生产经营方式'")
    private String productType;

    @Column(columnDefinition="COMMENT '行业分类代码'")
    private Integer industryNumber;

    @Column(columnDefinition="COMMENT '所属行业'")
    private String industryType;

    @Column(columnDefinition="COMMENT '行政隶属关系'")
    private String companyAttribute;

    @Column(columnDefinition="COMMENT '登记注册类型'")
    private String logoType;

    @Column(columnDefinition="COMMENT '从业人员（人）'")
    private Integer personNumber;

    @Column(columnDefinition="COMMENT '企业规模'")
    private String companyType;

    @Column(columnDefinition="COMMENT '安全生产许可证'")
    private String licence;

    @Column(columnDefinition="COMMENT '编号'")
    private Long number;

    @Column(columnDefinition="COMMENT '发证机关'")
    private String organName;

    @Column(columnDefinition="COMMENT '有效期_S'")
    private Date licenceStartDate;

    @Column(columnDefinition="COMMENT '有效期'")
    private Date licenceEndDate;

    @Column(columnDefinition="COMMENT '许可范围含危险化学品'")
    private Boolean hazardousChemicals;

    @Column(columnDefinition="COMMENT '列入本年度执法计划'")
    private Boolean lawPlan;

    @Column(columnDefinition="COMMENT '注销'")
    private Boolean cancellation;

    @Column(columnDefinition="COMMENT '负责人：'")
    private String head;

    @Column(columnDefinition="COMMENT '填表人'")
    private String fillPerson;

    @Column(columnDefinition="COMMENT '填报日期'")
    private Date createTableDate;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSCC() {
        return SCC;
    }

    public void setSCC(String SCC) {
        this.SCC = SCC;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getSpecial() {
        return special;
    }

    public void setSpecial(Boolean special) {
        this.special = special;
    }

    public Boolean getScale() {
        return scale;
    }

    public void setScale(Boolean scale) {
        this.scale = scale;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getIndustryNumber() {
        return industryNumber;
    }

    public void setIndustryNumber(Integer industryNumber) {
        this.industryNumber = industryNumber;
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public String getCompanyAttribute() {
        return companyAttribute;
    }

    public void setCompanyAttribute(String companyAttribute) {
        this.companyAttribute = companyAttribute;
    }

    public String getLogoType() {
        return logoType;
    }

    public void setLogoType(String logoType) {
        this.logoType = logoType;
    }

    public Integer getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(Integer personNumber) {
        this.personNumber = personNumber;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public Date getLicenceStartDate() {
        return licenceStartDate;
    }

    public void setLicenceStartDate(Date licenceStartDate) {
        this.licenceStartDate = licenceStartDate;
    }

    public Date getLicenceEndDate() {
        return licenceEndDate;
    }

    public void setLicenceEndDate(Date licenceEndDate) {
        this.licenceEndDate = licenceEndDate;
    }

    public Boolean getHazardousChemicals() {
        return hazardousChemicals;
    }

    public void setHazardousChemicals(Boolean hazardousChemicals) {
        this.hazardousChemicals = hazardousChemicals;
    }

    public Boolean getLawPlan() {
        return lawPlan;
    }

    public void setLawPlan(Boolean lawPlan) {
        this.lawPlan = lawPlan;
    }

    public Boolean getCancellation() {
        return cancellation;
    }

    public void setCancellation(Boolean cancellation) {
        this.cancellation = cancellation;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getFillPerson() {
        return fillPerson;
    }

    public void setFillPerson(String fillPerson) {
        this.fillPerson = fillPerson;
    }

    public Date getCreateTableDate() {
        return createTableDate;
    }

    public void setCreateTableDate(Date createTableDate) {
        this.createTableDate = createTableDate;
    }
}