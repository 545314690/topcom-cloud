//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.topcom.cms.domain;

import com.topcom.cms.base.model.BaseEntityModel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(
    name = "t_companyinfo"
)
public class CompanyInfo extends BaseEntityModel {
    private static final long serialVersionUID = 1L;


    private String companyName;
    private String managers;
    private String telephone;
    private String mobilePhone;
    private String email;
    private String regin;
    private String address;
    private String remarks;

    /**
     * 行业
     */
    private String industry;

    /**
     * 业务
     * @return
     */
    private String business;

    /**
     * 部门
     */
    private String department;


    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobilePhone() {
        return this.mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getManagers() {
        return this.managers;
    }

    public void setManagers(String managers) {
        this.managers = managers;
    }

    public String getRegin() {
        return regin;
    }

    public void setRegin(String regin) {
        this.regin = regin;
    }


    public CompanyInfo() {
    }
}
