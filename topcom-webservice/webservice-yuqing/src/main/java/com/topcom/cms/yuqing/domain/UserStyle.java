package com.topcom.cms.yuqing.domain;

import com.topcom.cms.base.model.BaseEntityModel;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * Created by topcom on 2017/8/2 0002.
 */
@Entity
@Table(name = "T_USERSTYLE")
@EntityListeners({AuditingEntityListener.class})
public class UserStyle extends BaseEntityModel {

//    @CreatedBy
//    @LastModifiedBy
    @Column(name = "userId",unique = true)
    private Long userId;

    private Long groupId;

    @Column(name = "style")
    private String style;

    @Column(name = "systemName")
    private String systemName;

    @Column(name = "iconUrl")
    private String iconUrl;

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //    public enum Type {
//        USER, DEFAULT
//    }
//    @Enumerated(EnumType.STRING)
//    @Column(name = "type")
//    public Type type;
//
//    public Type getType() {
//        return type;
//    }
//
//    public void setType(Type type) {
//        this.type = type;
//    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
