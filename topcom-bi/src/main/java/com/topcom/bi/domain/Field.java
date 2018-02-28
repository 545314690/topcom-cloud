package com.topcom.bi.domain;

/**
 * Created by lsm on 2018/2/28 0028.
 */
public class Field {
    public enum Type{
        STRING,NUMBER,DATE
    }
    private String name;
    private Type type;
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
