package com.topcom.cms.yuqing.domain;


import com.topcom.cms.base.model.BaseEntityModel;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 预警联系人
 */
@Entity
@Table(name = "t_contact")
@EntityListeners({AuditingEntityListener.class})
public class Contact extends BaseEntityModel {


    public enum Type {
        EMAIL, SMS, CLIENT,WECHAT
    }

    /**
     * 联系人名称
     */
    @Column(name = "name",nullable = false)
    private String name;

    /**
     * 联系人类型
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "type",nullable = false)
    private Type type;

    /**
     * 提醒联系人联系帐号
     */
    @Column(name = "account",nullable = false)
    private String account;

    /**
     * 用户ID
     */
    @CreatedBy
    @LastModifiedBy
    @Column(name = "userId",nullable = false)
    private Long userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


}
