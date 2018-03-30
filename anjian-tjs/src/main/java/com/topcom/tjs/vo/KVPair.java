package com.topcom.tjs.vo;

import java.io.Serializable;

/**
 * Created by lsm on 2018/3/27 0027.
 */
public class KVPair implements Serializable {
    private String name;
    private String value;

    public KVPair(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public KVPair() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
