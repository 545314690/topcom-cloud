package com.topcom.cms.vo;

import java.io.Serializable;

/**
 * @author maxl
 * @date 2018/3/12 0012
 */
public class ViewLogDto implements Serializable {
    private static final long serialVersionUID=1L;

    private Long count;
    private Long resourceId;
    /**
     * 菜单名称  view name
     */
    private String resourceName;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ViewLogDto(Long count, Long resourceId, String resourceName,String url) {
        this.count = count;
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.url =url;
    }


    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
