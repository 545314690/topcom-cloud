package com.topcom.cms.yuqing.vo.request;

import net.sf.json.JSONObject;

import java.io.Serializable;

/**
 * Created by topcom on 2017/8/14 0014.
 */
public class WechatSendRequest implements Serializable {
    /**
     * openid
     */
    private String touser;
    private String template_id;
    private String url;
    private JSONObject data;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }
}
