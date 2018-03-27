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
@Table(name = "tjs_organ")
public class TjsOrgan extends BaseEntityModel {

    /**
     *单位名称
     */
    @Column(columnDefinition="VARCHAR(50) COMMENT '单位名称'")
    private  String name;

    /**
     *单位级别
     */
    @Column(columnDefinition="VARCHAR(50) COMMENT '单位级别'")
    private  String type;

    /**
     *所在地区
     */
    @Column(columnDefinition="VARCHAR(50) COMMENT '所在地区'")
    private  String addr;

    @Column(columnDefinition="VARCHAR(20) COMMENT '省'")
    private String province;
    @Column(columnDefinition="VARCHAR(20) COMMENT '市'")
    private String city;
    @Column(columnDefinition="VARCHAR(20) COMMENT '县'")
    private String county;
    /**
     * 经纬度
     */
    @Column(columnDefinition="VARCHAR(50) COMMENT '经纬度'")
    private String lat;
    /**
     * 经纬度
     */
    @Column(columnDefinition="VARCHAR(50) COMMENT '经纬度'")
    private String lng;
    /**
     * 描述
     */
    @Column(columnDefinition="VARCHAR(255) COMMENT '描述'")
    private String description;

    /**
     *备注
     */
    @Column(columnDefinition="VARCHAR(50) COMMENT '备注'")
    private  String remarks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
