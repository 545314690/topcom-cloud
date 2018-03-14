package com.topcom.bi.domain;

import net.sf.json.JSONObject;

/**
 * Created by lsm on 2018/3/5 0005.
 */
public class SampleItem{
    private String name;
    private String description;
    private JSONObject option;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public JSONObject getOption() {
        return option;
    }

    public void setOption(JSONObject option) {
        this.option = option;
    }
}
