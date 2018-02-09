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
    /**
     * 用户类型
     */
    private int type;
    private String companyName;
    private String managers;
    private String telephone;
    private String mobilePhone;
    private String email;
    private String regin;
    private String address;
    private String remarks;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public UserInfo() {
    }
}
