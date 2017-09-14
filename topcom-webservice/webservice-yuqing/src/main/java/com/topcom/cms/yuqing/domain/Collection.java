package com.topcom.cms.yuqing.domain;

import com.topcom.cms.base.model.BaseEntityModel;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 我的收藏实体
 *
 * @author <a href="mailto:545314690@qq.om">lisenmiao</a>
 * @date 2015年2月25日下午2:11:50
 */
@Entity
@Table(name = "t_collection")
@EntityListeners({AuditingEntityListener.class})
public class Collection extends BaseEntityModel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    /**
     * 关联的用户id
     */
    @CreatedBy
    @LastModifiedBy
    protected Long userId;

    /**
     * 类型
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    MediaType type;

    /**
     * 在数据库中的id
     */
    @Column(name = "oId")
    String oId;

    /**
     * 标题
     */
    @Column(name = "title")
    String title;

    /**
     * 发布时间
     */
    @Column(name = "pubTime")
    String pubTime;

    /**
     * 来源
     */
    @Column(name = "source")
    String source;

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
