package com.topcom.tjs.vo;

import java.io.Serializable;

/**
 * Created by lsm on 2018/4/9 0009.
 */
public class CompanyVO implements Serializable {
    private Long id;
    /**
     * 经纬度
     */
    private Double lat;
    /**
     * 经纬度
     */
    private Double lng;

    private String companyName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
