package com.topcom.bi.domain;

import com.topcom.cms.base.model.nosql.BaseEntityModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by lsm on 2018/2/28 0028.
 */
@Document(collection = "tcbdap_interface")
public class Interface extends BaseEntityModel {
    private String sysName;
    private String name;
    private String description;
    private String path;
    private RequestMethod method;
    private String returnType = "JSON";
    private List<Field> requestParams;
    private List<Field> returnValues;

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public RequestMethod getMethod() {
        return method;
    }

    public void setMethod(RequestMethod method) {
        this.method = method;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public List<Field> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(List<Field> requestParams) {
        this.requestParams = requestParams;
    }

    public List<Field> getReturnValues() {
        return returnValues;
    }

    public void setReturnValues(List<Field> returnValues) {
        this.returnValues = returnValues;
    }
}