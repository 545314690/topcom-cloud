package com.topcom.cms.yuqing.domain;

import com.topcom.cms.base.model.BaseEntityModel;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 自定义专题实体
 *
 * @author <a href="mailto:545314690@qq.om">lisenmiao</a>
 * @date 2014年9月12日上午9:54:49
 */
@Entity
@Table(name = "t_customsubject")
@EntityListeners({AuditingEntityListener.class})
public class CustomSubject extends BaseEntityModel {


    /**
     * 报告状态
     */
    public enum State {
        CREATING, COMPLETED
    }

    /**
     *
     */
    private static final long serialVersionUID = 3628796007068348253L;

    /**
     * 关联的用户id
     */
    @CreatedBy
    @LastModifiedBy
    private Long userId;

    @Enumerated(value = EnumType.STRING)
    private State state = State.CREATING;
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

    /**
     * 开始时间
     */
    @Column(name = "startDate")
    Date startDate;

    /**
     * 结束时间
     */
    @Column(name = "endDate")
    Date endDate;

    /**
     * 专题名称
     */
    @Column(name = "name")
    String name;

    /**
     * 功能描述
     */
    @Column(name = "description")
    String description;


    /**
     * 领导舆情中领导id
     */
    @Column(name = "staffIds")
    private Long[] staffIds;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "warningTask_id")
    WarningTask warning;

    boolean enableWarning = false;

    public Long[] getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(Long[] staffIds) {
        this.staffIds = staffIds;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getShouldWord() {
        return shouldWord;
    }

    public void setShouldWord(String shouldWord) {
        this.shouldWord = shouldWord;
    }

    public String getMustWord() {
        return mustWord;
    }

    public void setMustWord(String mustWord) {
        this.mustWord = mustWord;
    }

    public String getMustNotWord() {
        return mustNotWord;
    }

    public void setMustNotWord(String mustNotWord) {
        this.mustNotWord = mustNotWord;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public WarningTask getWarning() {
        return warning;
    }

    public void setWarning(WarningTask warning) {
        this.warning = warning;
    }

    public boolean isEnableWarning() {
        return enableWarning;
    }

    public void setEnableWarning(boolean enableWarning) {
        this.enableWarning = enableWarning;
    }
}
