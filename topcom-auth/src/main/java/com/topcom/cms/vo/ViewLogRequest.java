package com.topcom.cms.vo;

import java.util.List;

/**
 * @author maxl
 * @date 2018/3/12 0012
 */
public class ViewLogRequest {

    private List<Long>  resourceIds;
    private int limit;

    public List<Long> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<Long> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
