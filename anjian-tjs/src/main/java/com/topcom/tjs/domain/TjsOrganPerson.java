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
    @Column(columnDefinition="COMMENT '单位名称'")
    private  String organ_name;

    /**
     *单位级别
     */
    @Column(columnDefinition="COMMENT '单位级别'")
    private  String organ_type;

    /**
     *所在地区
     */
    @Column(columnDefinition="COMMENT '所在地区'")
    private  String organ_addr;

    /**
     *姓名
     */
    @Column(columnDefinition="COMMENT '姓名'")
    private  String name;

    /**
     *性别
     */
    @Column(columnDefinition="COMMENT '性别'")
    private  String gender;

    /**
     *年龄
     */
    @Column(columnDefinition="COMMENT '年龄'")
    private  Integer age;

    /**
     *行政级别
     */
    @Column(columnDefinition="COMMENT '行政级别'")
    private  String level;

    /**
     *执法证编号
     */
    @Column(columnDefinition="COMMENT '执法证编号'")
    private  String number;

    /**
     *备注
     */
    @Column(columnDefinition="COMMENT '备注'")
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
