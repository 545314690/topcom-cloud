package com.topcom.cms.yuqing.domain;

import com.topcom.cms.base.model.BaseEntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 简报实体
 *
 * @author <a href="mailto:545314690@qq.om">lisenmiao</a>
 * @date 2014年10月11日下午3:23:52
 */
@Entity
@Table(name = "t_warning_log")
public class WarningLog extends BaseEntityModel {

    private Long warningTaskId;

    private Long subjectId;
    private Long userId;

    private String subjectName;

    private String[] sentimentLabel;


    private String[] type;
    private String field;

    /**
     * 必须出现的词，"@"分割
     */
    @Column(name = "mustWord", nullable = false)
    private String mustWord;

    /**
     * 同现词，至少出现其中一个，"@"分割
     */
    private String shouldWord;

    /**
     * 过滤词，不能出现的词，"@"分割
     */
    private String mustNotWord;


    @Column(name = "startDate")
    private String startDate;

    @Column(name = "endDate")
    private String endDate;

    public String[] getSentimentLabel() {
        return sentimentLabel;
    }

    public void setSentimentLabel(String[] sentimentLabel) {
        this.sentimentLabel = sentimentLabel;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public String getMustWord() {
        return mustWord;
    }

    public void setMustWord(String mustWord) {
        this.mustWord = mustWord;
    }

    public String getShouldWord() {
        return shouldWord;
    }

    public void setShouldWord(String shouldWord) {
        this.shouldWord = shouldWord;
    }

    public String getMustNotWord() {
        return mustNotWord;
    }

    public void setMustNotWord(String mustNotWord) {
        this.mustNotWord = mustNotWord;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getWarningTaskId() {
        return warningTaskId;
    }

    public void setWarningTaskId(Long warningTaskId) {
        this.warningTaskId = warningTaskId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
