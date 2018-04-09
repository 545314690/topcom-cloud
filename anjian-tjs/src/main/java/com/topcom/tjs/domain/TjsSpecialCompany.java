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

    @Column(columnDefinition="VARCHAR(500) COMMENT '单位名称'")
    private String companyName;

    @Column(columnDefinition="VARCHAR(50) COMMENT '统一社会信用代码'")
    private String SCC;

    @Column(columnDefinition="VARCHAR(50) COMMENT '注册地址'")
    private String address;

    @Column(columnDefinition="VARCHAR(50) COMMENT '省'")
    private String province;
    @Column(columnDefinition="VARCHAR(50) COMMENT '市'")
    private String city;
    @Column(columnDefinition="VARCHAR(50) COMMENT '县'")
    private String county;
    /**
     * 经纬度
     */
    @Column(columnDefinition="DOUBLE(9,6) COMMENT '经纬度'")
    private Double lat;
    /**
     * 经纬度
     */
    @Column(columnDefinition="DOUBLE(9,6) COMMENT '经纬度'")
    private Double lng;

    @Column(columnDefinition="BIT COMMENT '列入安全生产监管重点企业'")
    private Boolean special;

    @Column(columnDefinition="BIT COMMENT '规模以上生产经营单位'")
    private Boolean scale;

    @Column(columnDefinition="VARCHAR(50) COMMENT '生产经营方式'")
    private String productType;

    @Column(columnDefinition="INT COMMENT '行业分类代码'")
    private Integer industryNumber;

    @Column(columnDefinition="VARCHAR(50) COMMENT '所属行业'")
    private String industryType;

    @Column(columnDefinition="VARCHAR(50) COMMENT '行政隶属关系'")
    private String companyAttribute;

    @Column(columnDefinition="VARCHAR(50) COMMENT '登记注册类型'")
    private String logoType;

    @Column(columnDefinition="INT COMMENT '从业人员（人）'")
    private Integer personNumber;

    @Column(columnDefinition="VARCHAR(50) COMMENT '企业规模'")
    private String companyType;

    @Column(columnDefinition="VARCHAR(50) COMMENT '安全生产许可证'")
    private String licence;

    @Column(columnDefinition="VARCHAR(50) COMMENT '编号'")
    private Long number;

    @Column(columnDefinition="VARCHAR(50) COMMENT '发证机关'")
    private String organName;

    @Column(columnDefinition="DATETIME COMMENT '有效期_S'")
    private Date licenceStartDate;

    @Column(columnDefinition="DATETIME COMMENT '有效期'")
    private Date licenceEndDate;

    @Column(columnDefinition="BIT COMMENT '许可范围含危险化学品'")
    private Boolean hazardousChemicals;

    @Column(columnDefinition="BIT COMMENT '列入本年度执法计划'")
    private Boolean lawPlan;

    @Column(columnDefinition="BIT COMMENT '注销'")
    private Boolean cancellation;

    @Column(columnDefinition="VARCHAR(50) COMMENT '负责人：'")
    private String head;

    @Column(columnDefinition="VARCHAR(50) COMMENT '填表人'")
    private String fillPerson;

    @Column(columnDefinition="DATETIME COMMENT '填报日期'")
    private Date createTableDate;

    @Column(columnDefinition="DATETIME COMMENT '管理分类'")
    private String GLFL;

    public String getGLFL() {
        return GLFL;
    }

    public void setGLFL(String GLFL) {
        this.GLFL = GLFL;
    }

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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
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