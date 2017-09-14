package com.topcom.cms.yuqing.domain;

import com.topcom.cms.base.model.BaseEntityModel;
import com.topcom.cms.yuqing.utils.CronExpression;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by maxl on 17-6-9.
 */

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class BriefingBaseTask extends BaseEntityModel {

    @Transient
    protected CronExpression cronExpression = new CronExpression();

    @Column
    protected boolean enable = false;

    /**
     * 预警使用以及日报使用，
     * 预警的时候指的是每隔几个小时，日报指的是生成时间
     */
    @Column
    protected Integer hours;

    /**
     * 月报与周报使用，周报时为周几生成周报，
     * 月报时，取值范-1指的是月末最后一天1指的是月初第一天
     */
    @Column
    protected Integer day;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    protected Briefing.BriefingType briefingType;


    protected String cron;


    @CreatedBy
//    @LastModifiedBy  (系统会修改task，加上之后userId会被抹去)
    @Column(name = "userId")
    protected Long userId;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Briefing.BriefingType getBriefingType() {
        return briefingType;
    }

    public void setBriefingType(Briefing.BriefingType briefingType) {
        this.briefingType = briefingType;
    }

    public String getCron() {
        return cron;
    }

    /**
     * 改写set方法
     *
     * @param cron
     */
    public void setCron(String cron) {
        this.cron = this.cronExpression.toString();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public CronExpression getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(CronExpression cronExpression) {
        this.cronExpression = cronExpression;
    }
}
