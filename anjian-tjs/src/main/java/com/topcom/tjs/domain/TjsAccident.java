package com.topcom.tjs.domain;

import com.topcom.cms.base.model.BaseEntityModel;

import javax.persistence.*;
import java.util.Date;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
@Entity
@Table(name = "tjs_accident")
public class TjsAccident extends BaseEntityModel {
    @ManyToOne
    @JoinColumn(name = "companyId",columnDefinition="bigint(20) COMMENT '企业id'")
    private TjsSpecialCompany company;
    /**
     * 事故发生单位
     */
    @Column(columnDefinition="varchar(100) COMMENT '事故发生单位'")
    private String companyName;
    /**
     * 管理分类
     */
    @Column(columnDefinition="varchar(20) COMMENT '管理分类'")
    private String manageType;
    /**
     * 事故类型
     */
    @Column(columnDefinition="varchar(20) COMMENT '事故类型'")
    private String type;
    /**
     * 事故发生地点
     */
    @Column(columnDefinition="varchar(255) COMMENT '事故发生地点'")
    private String address;
    @Column(columnDefinition="varchar(20) COMMENT '省'")
    private String province;
    @Column(columnDefinition="varchar(20) COMMENT '市'")
    private String city;
    @Column(columnDefinition="varchar(20) COMMENT '县'")
    private String county;
    /**
     * 经纬度
     */
    @Column(columnDefinition="varchar(30) COMMENT '经纬度'")
    private String lat;
    /**
     * 经纬度
     */
    @Column(columnDefinition="varchar(30) COMMENT '经纬度'")
    private String lng;
    /**
     * 事故发生时间
     */
    @Column(columnDefinition="datetime COMMENT '事故发生时间'")
    private Date happenedTime;
    /**
     * 死亡(下落不明）人数
     */
    @Column(columnDefinition="bigint(20) COMMENT '死亡(下落不明）人数'")
    private Integer deathNumber;
    /**
     * 受伤人数
     */
    @Column(columnDefinition="bigint(20) COMMENT '受伤人数'")
    private Integer injuredNumber;
    /**
     * 重伤人数
     */
    @Column(columnDefinition="bigint(20) COMMENT '重伤人数'")
    private Integer terribleNumber;
    /**
     * 事故概况
     */
    @Column(columnDefinition="varchar(1500) COMMENT '事故概况'")
    private String profile;
    /**
     * 事故发生单位详细情况
     */
    @Column(columnDefinition="varchar(500) COMMENT '事故发生单位详细情况'")
    private String companyProfile;
    /**
     * 社会信用代码
     */
    @Column(columnDefinition="varchar(30) COMMENT '社会信用代码'")
    private String SCC;
    /**
     * 单位规模
     */
    @Column(columnDefinition="varchar(20) COMMENT '单位规模'")
    private String companyScale;
    /**
     * 直接经济损失
     */
    @Column(columnDefinition="double COMMENT '直接经济损失'")
    private Double loss;
    /**
     * 国有企业属性
     */
    @Column(columnDefinition="varchar(20) COMMENT '国有企业属性'")
    private String companyAttribute;
    /**
     * 是否为举报事故
     */
    @Column(columnDefinition="bit(1) COMMENT '是否为举报事故'")
    private Boolean report;
    /**
     * 是否涉及相关因素
     */
    @Column(columnDefinition="varchar(20) COMMENT '是否涉及相关因素'")
    private String factors;
    /**
     * 登记注册类型
     */
    @Column(columnDefinition="varchar(20) COMMENT '登记注册类型'")
    private String companyType;
    /**
     * 所属行业
     */
    @Column(columnDefinition="varchar(20) COMMENT '所属行业'")
    private String companyIndustry;
    /**
     * 事故详细情况
     */
    @Column(columnDefinition="varchar(500) COMMENT '事故详细情况'")
    private String description;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getManageType() {
        return manageType;
    }

    public void setManageType(String manageType) {
        this.manageType = manageType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public Date getHappenedTime() {
        return happenedTime;
    }

    public void setHappenedTime(Date happenedTime) {
        this.happenedTime = happenedTime;
    }

    public Integer getDeathNumber() {
        return deathNumber;
    }

    public void setDeathNumber(Integer deathNumber) {
        this.deathNumber = deathNumber;
    }

    public Integer getInjuredNumber() {
        return injuredNumber;
    }

    public void setInjuredNumber(Integer injuredNumber) {
        this.injuredNumber = injuredNumber;
    }

    public Integer getTerribleNumber() {
        return terribleNumber;
    }

    public void setTerribleNumber(Integer terribleNumber) {
        this.terribleNumber = terribleNumber;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getCompanyProfile() {
        return companyProfile;
    }

    public void setCompanyProfile(String companyProfile) {
        this.companyProfile = companyProfile;
    }

    public String getSCC() {
        return SCC;
    }

    public void setSCC(String SCC) {
        this.SCC = SCC;
    }

    public String getCompanyScale() {
        return companyScale;
    }

    public void setCompanyScale(String companyScale) {
        this.companyScale = companyScale;
    }

    public Double getLoss() {
        return loss;
    }

    public void setLoss(Double loss) {
        this.loss = loss;
    }

    public String getCompanyAttribute() {
        return companyAttribute;
    }

    public void setCompanyAttribute(String companyAttribute) {
        this.companyAttribute = companyAttribute;
    }

    public Boolean getReport() {
        return report;
    }

    public void setReport(Boolean report) {
        this.report = report;
    }

    public String getFactors() {
        return factors;
    }

    public void setFactors(String factors) {
        this.factors = factors;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompanyIndustry() {
        return companyIndustry;
    }

    public void setCompanyIndustry(String companyIndustry) {
        this.companyIndustry = companyIndustry;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TjsSpecialCompany getCompany() {
        return company;
    }

    public void setCompany(TjsSpecialCompany company) {
        this.company = company;
    }
}
