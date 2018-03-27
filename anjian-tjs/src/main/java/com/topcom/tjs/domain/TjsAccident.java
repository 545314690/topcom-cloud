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
@Table(name = "tjs_accident")
public class TjsAccident extends BaseEntityModel {

    /**
     * 事故发生单位
     */
    @Column(columnDefinition="COMMENT '事故发生单位'")
    private String company;
    /**
     * 管理分类
     */
    @Column(columnDefinition="COMMENT '管理分类'")
    private String manageType;
    /**
     * 事故类型
     */
    @Column(columnDefinition="COMMENT '事故类型'")
    private String type;
    /**
     * 事故发生地点
     */
    @Column(columnDefinition="COMMENT '事故发生地点'")
    private String address;
    /**
     * 经纬度
     */
    @Column(columnDefinition="COMMENT '经纬度'")
    private Double lat;
    /**
     * 经纬度
     */
    @Column(columnDefinition="COMMENT '经纬度'")
    private Double lng;
    /**
     * 事故发生时间
     */
    @Column(columnDefinition="COMMENT '事故发生时间'")
    private Date haddenedTime;
    /**
     * 死亡(下落不明）人数
     */
    @Column(columnDefinition="COMMENT '死亡(下落不明）人数'")
    private Integer deathNumber;
    /**
     * 受伤人数
     */
    @Column(columnDefinition="COMMENT '受伤人数'")
    private Integer injuredNumber;
    /**
     * 重伤人数
     */
    @Column(columnDefinition="COMMENT '重伤人数'")
    private Integer terribleNumber;
    /**
     * 事故概况
     */
    @Column(columnDefinition="COMMENT '事故概况'")
    private String profile;
    /**
     * 事故发生单位详细情况
     */
    @Column(columnDefinition="COMMENT '事故发生单位详细情况'")
    private String companyProfile;
    /**
     * 社会信用代码
     */
    @Column(columnDefinition="COMMENT '社会信用代码'")
    private String SCC;
    /**
     * 单位规模
     */
    @Column(columnDefinition="COMMENT '单位规模'")
    private String companyScale;
    /**
     * 直接经济损失
     */
    @Column(columnDefinition="COMMENT '直接经济损失'")
    private Double loss;
    /**
     * 国有企业属性
     */
    @Column(columnDefinition="COMMENT '国有企业属性'")
    private String companyAttribute;
    /**
     * 是否为举报事故
     */
    @Column(columnDefinition="COMMENT '是否为举报事故'")
    private String report;
    /**
     * 是否涉及相关因素
     */
    @Column(columnDefinition="COMMENT '是否涉及相关因素'")
    private String factors;
    /**
     * 登记注册类型
     */
    @Column(columnDefinition="COMMENT 登记注册类型''")
    private String companyType;
    /**
     * 所属行业
     */
    @Column(columnDefinition="COMMENT '所属行业'")
    private String companyIndustry;
    /**
     * 事故详细情况
     */
    @Column(columnDefinition="COMMENT '事故详细情况'")
    private String describe;


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public Date getHaddenedTime() {
        return haddenedTime;
    }

    public void setHaddenedTime(Date haddenedTime) {
        this.haddenedTime = haddenedTime;
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

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
