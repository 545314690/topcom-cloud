//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.topcom.cms.domain;

import com.topcom.cms.base.model.BaseEntityModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(
    name = "t_login_log"
)
public class LoginLog extends BaseEntityModel {
    @Column(
        name = "username"
    )
    String username;
    @Column(
        name = "userId"
    )
    Long userId;
    @Column(
        name = "host"
    )
    String host;
    @Column(
        name = "sysName"
    )
    String sysName;

    public LoginLog() {
    }

    public String getSysName() {
        return this.sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
