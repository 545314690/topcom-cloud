package com.topcom.cms.yuqing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.topcom.cms.base.model.nosql.BaseEntityModel;
import com.topcom.cms.yuqing.config.Constants;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.List;

/**
 * 简报实体
 *
 * @author <a href="mailto:545314690@qq.om">lisenmiao</a>
 * @date 2014年10月11日下午3:23:52
 */
//@Entity
//@Table(name = "t_brief")
@Document(collection = Constants.Mongo.COLLECTION_BRIEFING)
@EntityListeners({AuditingEntityListener.class})
public class Briefing extends BaseEntityModel {


    public enum BriefingType {//日报、周报、月报、季报、半年报、年报、专报
        DAILY, WEEKLY, MONTHLY, QUARTERLY, SEMIANNUAL, ANNUAL, SPECIAL,BASE_MONTHLY,BASE_SPECIAL
    }

    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    @CreatedBy
    @LastModifiedBy
    private Long userId;
    /**
     * 报告类型
     */
    @Enumerated(value = EnumType.STRING)
    private BriefingType type;

    /**
     * 创建者
     */
    private String author;
    /**
     * 如果是专题报告，此处为专题id
     */
    private Long subjectId;

    /**
     * 简报期号
     */
    private String issue;

    /**
     * 简报标题
     */
    private String title;
    /**
     * 简报二级标题
     */
    private String subTitle;
    /**
     * 概述
     */
    private String outline;
    /**
     * 总结
     */
    private String summary;
    /**
     * 模版名
     */
    private String template;

    /**
     * 附件
     */
    private String[] attachment;

    private String createTime;


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Transient
    @JsonIgnore
    private List<BriefingCell> briefingBody;

    public List<BriefingCell> getBriefingBody() {
        return briefingBody;
    }

    public void setBriefingBody(List<BriefingCell> briefingBody) {
        this.briefingBody = briefingBody;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BriefingType getType() {
        return type;
    }

    public void setType(BriefingType type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String[] getAttachment() {
        return attachment;
    }

    public void setAttachment(String... attachment) {
        this.attachment = attachment;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
}
