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
    name = "t_userinfo"
)
public class UserInfo extends BaseEntityModel {
    private static final long serialVersionUID = 1L;
    String companyName;
    String managers;
    String telephone;
    String mobilePhone;
    String email;
    String address;
    String remarks;

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

    public UserInfo() {
    }
}
