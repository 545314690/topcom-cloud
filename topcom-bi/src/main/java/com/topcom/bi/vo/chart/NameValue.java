package com.topcom.bi.vo.chart;

/**
 * Created by lsm on 2018/3/2 0002.
 */
public class NameValue {
    private String name;
    private Object value;

    public NameValue() {
    }

    public NameValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
