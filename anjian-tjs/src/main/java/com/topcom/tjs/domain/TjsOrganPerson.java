package com.topcom.tjs.domain;

import com.topcom.cms.base.model.BaseEntityModel;

import javax.persistence.*;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
@Entity
@Table(name = "tjs_organ_person")
public class TjsOrganPerson extends BaseEntityModel {

    @ManyToOne
    @JoinColumn(name = "organId",columnDefinition="bigint(20) COMMENT '组织机构id'")
    private TjsOrgan organ;
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

    /**
     * 文化程度
     */
    @Column(columnDefinition="VARCHAR(50) COMMENT '文化程度'")
    private String education;

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
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

    public TjsOrgan getOrgan() {
        return organ;
    }

    public void setOrgan(TjsOrgan organ) {
        this.organ = organ;
    }
}
