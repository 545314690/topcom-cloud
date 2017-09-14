package com.topcom.cms.yuqing.domain;

import com.topcom.cms.base.model.BaseEntityModel;
import com.topcom.cms.common.model.Sentiment;
import com.topcom.cms.yuqing.utils.CronExpression;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 简报实体
 *
 * @author <a href="mailto:545314690@qq.om">lisenmiao</a>
 * @date 2014年10月11日下午3:23:52
 */
@Entity
@Table(name = "t_warning")
public class Warning extends BaseEntityModel {


    private String[] sentimentLabel;


    private String[] type;

    @Column(nullable = false)
    private String receiveStartTime;
    @Column(nullable = false)
    private String receiveEndTime;

    /**
     * 是否周末预警,默认否
     */
    private Boolean atWeekends = false;

    /**
     * 预警间隔（小时）
     */
    @Column(nullable = false)
    private Integer hours;


    @Column(nullable = false)
    private String corn;

    /**
     * 上次预警时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastWarningDate")
    private Date lastWarningDate;


    /**
     * 预警联系人
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "t_warning_contact",
            joinColumns = @JoinColumn(name = "warningId"),
            inverseJoinColumns = @JoinColumn(name = "contactId")
    )
    private List<Contact> contacts;

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

    public String getReceiveStartTime() {
        return receiveStartTime;
    }

    public void setReceiveStartTime(String receiveStartTime) {
        this.receiveStartTime = receiveStartTime;
    }

    public String getReceiveEndTime() {
        return receiveEndTime;
    }

    public void setReceiveEndTime(String receiveEndTime) {
        this.receiveEndTime = receiveEndTime;
    }

    public Boolean getAtWeekends() {
        return atWeekends;
    }

    public void setAtWeekends(Boolean atWeekends) {
        this.atWeekends = atWeekends;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public String getCorn() {
//        CronExpression cronExpression = new CronExpression();
//        if (atWeekends) {
//            cronExpression.setWeek("MON-FRI");
//        }
//        cronExpression.setType(CronExpression.Type.INTERVAL);
//        cronExpression.setHour(receiveStartTime + "-" + receiveEndTime + "/" + String.valueOf(hours));
//        cron = cronExpression.toString();
        return corn;
    }

    public void setCorn(String corn) {
        this.corn = corn;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Date getLastWarningDate() {
        return lastWarningDate;
    }

    public void setLastWarningDate(Date lastWarningDate) {
        this.lastWarningDate = lastWarningDate;
    }

    public static void main(String[] args) {
        Warning warning = new Warning();
        warning.setHours(2);
        warning.setReceiveStartTime("8");
        warning.setReceiveEndTime("18");
        warning.setAtWeekends(true);
        warning.setCorn(null);
        System.out.println(warning.getCorn());
    }
}
