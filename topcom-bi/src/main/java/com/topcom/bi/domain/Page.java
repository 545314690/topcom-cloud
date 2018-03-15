package com.topcom.bi.domain;

import com.topcom.bi.utils.JsonUtil;
import com.topcom.cms.base.model.nosql.BaseEntityModel;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsm on 2018/3/2 0002.
 */
@Document(collection = "tcbdap_page")
public class Page extends BaseEntityModel {
    private String name;
    private int sort;
    private JSONObject pageConfig;
    private List<GridsterItem> gridsterItems = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public List<GridsterItem> getGridsterItems() {
        return gridsterItems;
    }

    public void setGridsterItems(List<GridsterItem> gridsterItems) {
        this.gridsterItems = gridsterItems;
    }

    public JSONObject getPageConfig() {
        return pageConfig;
    }

    public void setPageConfig(JSONObject pageConfig) {
        this.pageConfig =JsonUtil.toJsonNoNull(pageConfig);
    }
}
