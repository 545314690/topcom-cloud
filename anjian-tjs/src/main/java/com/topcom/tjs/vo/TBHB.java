package com.topcom.tjs.vo;

import java.io.Serializable;

/**
 * 同比环比VO
 * Created by lsm on 2018/3/27 0027.
 */
public class TBHB implements Serializable {
    private String metricName;
    private String value;
    private String date;
    private String unit;

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
