package com.topcom.bi.domain;

import com.topcom.bi.utils.JsonUtil;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Created by lsm on 2018/3/2 0002.
 */
public class GridsterItem {
    private String id;
    private String type;
    private JSONObject option;
    private JSONObject data;
    private JSONObject style;
    private Interface dataInterface;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JSONObject getOption() {
        return option;
    }

    public void setOption(JSONObject option) {
        this.option = JsonUtil.toJsonNoNull(option);
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = JsonUtil.toJsonNoNull(data);
    }

    public JSONObject getStyle() {
        return style;
    }

    public void setStyle(JSONObject style) {
        this.style = JsonUtil.toJsonNoNull(style);
    }

    public Interface getDataInterface() {
        return dataInterface;
    }

    public void setDataInterface(Interface dataInterface) {
        this.dataInterface = dataInterface;
    }
}
