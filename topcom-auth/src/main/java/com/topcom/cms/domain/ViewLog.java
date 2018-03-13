package com.topcom.cms.domain;

import com.topcom.cms.base.model.BaseEntityModel;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

/**
 * 访问日志实体类
 * 存储用户点击系统菜单产生的日志信息 包含用户信息及点击行为信息
 * @author maxl
 * @date 2018/3/12 0012
 */
@Entity
@Table(
        name = "t_viewLog"
)
@EntityListeners({AuditingEntityListener.class})
public class ViewLog extends BaseEntityModel {


    @CreatedBy
    @LastModifiedBy
    private Long userId;

    /**
     * 菜单id 资源id
     */
    private Long resourceId;
    /**
     * 菜单名称  view name
     */
    private String resourceName;

    /**
     * 跳转页面。也就是上一个页面
     */
    private Long lastResourceId;

    /**
     * 访问次数（备用）
     */
    private Long viewSum;

    public Long getViewSum() {
        return viewSum;
    }

    public void setViewSum(Long viewSum) {
        this.viewSum = viewSum;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Long getLastResourceId() {
        return lastResourceId;
    }

    public void setLastResourceId(Long lastResourceId) {
        this.lastResourceId = lastResourceId;
    }
}
