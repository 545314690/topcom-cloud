package com.topcom.cms.yuqing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.topcom.cms.base.model.BaseEntityModel;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.*;
import java.util.List;

/**
 * 微信公众号关注者信息
 * Created by topcom on 2017/8/14 0014.
 */
@Entity
@Table(name = "t_subscription_follower")
public class SubscriptionFollower extends BaseEntityModel{
    private Integer subscribe;
    private String openid;
    private String nickname;
    private Integer sex;
    private String language;
    private String city;
    private String country;
    private String headimgurl;
    private Long subscribe_time;
    private String remark;
    private Long groupid;


    //@ElementCollection
    @JsonIgnore
    @Transient
    private List<String> tagid_list;

    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public Long getSubscribe_time() {
        return subscribe_time;
    }

    public void setSubscribe_time(Long subscribe_time) {
        this.subscribe_time = subscribe_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public List<String> getTagid_list() {
        return tagid_list;
    }

    public void setTagid_list(List<String> tagid_list) {
        this.tagid_list = tagid_list;
    }
}
