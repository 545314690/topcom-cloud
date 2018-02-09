//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.topcom.cms.domain;

import com.topcom.cms.base.model.BaseEntityModel;
import com.topcom.cms.base.model.BaseTreeEntityModel;

import javax.persistence.*;

@Entity
@Table(
        name = "t_region"
)
/**
 * 区域实体（省市县三级联动）
 * @author lism
 */
public class Region extends BaseEntityModel {
    private static final long serialVersionUID = -7728256029970557576L;
    private int parentId;
    private String name;
    private int code;
    private String shortName;
    private double longitude;
    private double latitude;

    private Level level;
    private int sort;

    public enum Level {
        COUNTRY, PROVINCE, CITY, COUNTY, STREET, SPECIAL//特别行政区
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
