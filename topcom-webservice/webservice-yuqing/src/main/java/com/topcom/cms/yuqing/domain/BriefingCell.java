package com.topcom.cms.yuqing.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import net.sf.json.JSONObject;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by lism on 14:04.
 * Author：<a href="mailto:545314690@qq.om">lisenmiao</a>
 */
public class BriefingCell implements Serializable{

    private String level;
    private String title;
    private String imageUrl;
    private Map option;
    private String charId;
    private String method;
    private String description;
    private String charType;


    public Map getOption() {
        return option;
    }

    public void setOption(Map option) {
        this.option = option;
    }

    public String getCharType() {
        return charType;
    }

    public void setCharType(String charType) {
        this.charType = charType;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getCharId() {
        return charId;
    }

    public void setCharId(String charId) {
        this.charId = charId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BriefingCell> getChildren() {
        return children;
    }

    public void setChildren(List<BriefingCell> children) {
        this.children = children;
    }

    @Transient
    private List<BriefingCell> children;


}
