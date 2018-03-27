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
@Table(name = "tjs_death_person")
public class TjsDeathPerson extends BaseEntityModel {
    /**
     * 姓名
     */
    @Column(columnDefinition=" VARCHAR(20) COMMENT '姓名'")
    private String name;

    /**
     * 身份证号码
     */
    @Column(columnDefinition="VARCHAR(50) COMMENT '身份证号码'")
    private String cardId;

    /**
     * 性别
     */
    @Column(columnDefinition="VARCHAR(10) COMMENT '性别'")
    private String gender;

    /**
     * 年龄
     */
    @Column(columnDefinition="INT COMMENT '年龄'")
    private Integer age;

    /**
     * 状态
     */
    @Column(columnDefinition="VARCHAR(10) COMMENT '状态'")
    private String status;

    /**
     * 死亡日期
     */
    @Column(columnDefinition="DATETIME COMMENT '死亡日期'")
    private Date deathDate;

    /**
     * 是否职业死亡
     */
    @Column(columnDefinition="BIT COMMENT '是否职业死亡'")
    private Boolean professionDeath;

    /**
     * 文化程度
     */
    @Column(columnDefinition="VARCHAR(50) COMMENT '文化程度'")
    private String education;

    /**
     * 职业
     */
    @Column(columnDefinition="VARCHAR(50) COMMENT '职业'")
    private String profession;

    /**
     * 有无工伤保险
     */
    @Column(columnDefinition="BIT COMMENT '有无工伤保险'")
    private Boolean industrialInsurance;


    /**
     * 事故
     */
    private TjsAccident accident;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public Boolean getProfessionDeath() {
        return professionDeath;
    }

    public void setProfessionDeath(Boolean professionDeath) {
        this.professionDeath = professionDeath;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Boolean getIndustrialInsurance() {
        return industrialInsurance;
    }

    public void setIndustrialInsurance(Boolean industrialInsurance) {
        this.industrialInsurance = industrialInsurance;
    }

    public TjsAccident getAccident() {
        return accident;
    }

    public void setAccident(TjsAccident accident) {
        this.accident = accident;
    }
}
