package com.topcom.tjs.domain;

import com.topcom.cms.base.model.BaseEntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
@Entity
@Table(name = "tjs_organ_person")
public class TjsOrganPerson extends BaseEntityModel {

    /**
     *单位名称
     */
    @Column(columnDefinition="VARCHAR(50) COMMENT '单位名称'")
    private  String organ_name;

    /**
     *单位级别
     */
    @Column(columnDefinition="VARCHAR(50) COMMENT '单位级别'")
    private  String organ_type;

    /**
     *所在地区
     */
    @Column(columnDefinition="VARCHAR(50) COMMENT '所在地区'")
    private  String organ_addr;

    @Column(columnDefinition="VARCHAR(20) COMMENT '省'")
    private String province;
    @Column(columnDefinition="VARCHAR(20) COMMENT '市'")
    private String city;
    @Column(columnDefinition="VARCHAR(20) COMMENT '县'")
    private String county;
    /**
     * 经纬度
     */
    @Column(columnDefinition="VARCHAR(50) COMMENT '经纬度'")
    private String lat;
    /**
     * 经纬度
     */
    @Column(columnDefinition="VARCHAR(50) COMMENT '经纬度'")
    private String lng;
    /**
     *姓名
     */
    @Column(columnDefinition="VARCHAR(20) COMMENT '姓名'")
    private  String name;

    /**
     *性别
     */
    @Column(columnDefinition="VARCHAR(10) COMMENT '性别'")
    private  String gender;

    /**
     *年龄
     */
    @Column(columnDefinition="INT COMMENT '年龄'")
    private  Integer age;

    /**
     *行政级别
     */
    @Column(columnDefinition="VARCHAR(50) COMMENT '行政级别'")
    private  String level;

    /**
     *执法证编号
     */
    @Column(columnDefinition="VARCHAR(50) COMMENT '执法证编号'")
    private  String number;

    /**
     *备注
     */
    @Column(columnDefinition="VARCHAR(50) COMMENT '备注'")
    private  String remarks;

    public String getOrgan_name() {
        return organ_name;
    }

    public void setOrgan_name(String organ_name) {
        this.organ_name = organ_name;
    }

    public String getOrgan_type() {
        return organ_type;
    }

    public void setOrgan_type(String organ_type) {
        this.organ_type = organ_type;
    }

    public String getOrgan_addr() {
        return organ_addr;
    }

    public void setOrgan_addr(String organ_addr) {
        this.organ_addr = organ_addr;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
