package com.topcom.cms.yuqing.domain;

import com.topcom.cms.yuqing.utils.CronExpression;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by maxl on 17-6-9.
 */
@Entity
@Table(name = "t_warning_task")
public class WarningTask extends BriefingBaseTask {

    private String[] sentimentLabel;


    private String[] type;
    /**
     * 关键词匹配的类型，默认title和content
     */
    private String filed;

    @Column(nullable = false)
    private String receiveStartTime;
    @Column(nullable = false)
    private String receiveEndTime;

    /**
     * 是否周末预警,默认否
     */
    private boolean atWeekends = false;
    /**
     * 上次预警时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastWarningDate")
    protected Date lastWarningDate;

    /**
     * 预警联系人
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "t_warning_task_contact",
            joinColumns = @JoinColumn(name = "warningTaskId"),
            inverseJoinColumns = @JoinColumn(name = "contactId")
    )
    private List<Contact> contacts;

    public int getMinWarningNum() {
        return minWarningNum;
    }

    public void setMinWarningNum(int minWarningNum) {
        this.minWarningNum = minWarningNum;
    }

    /**
     * 最少新闻条数，超过这个数目预警才能触发
     */
    private int minWarningNum=1;
    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

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

    public Date getLastWarningDate() {
        return lastWarningDate;
    }

    public void setLastWarningDate(Date lastWarningDate) {
        this.lastWarningDate = lastWarningDate;
    }

    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }

    public boolean isAtWeekends() {
        return atWeekends;
    }

    public void setAtWeekends(boolean atWeekends) {
        this.atWeekends = atWeekends;
    }

    /**
     * 需要重写，实现自己的表达式构建
     *
     * @return
     */
    @Override
    public String getCron() {
        CronExpression cronExpression = new CronExpression();
        if (atWeekends) {
            cronExpression.setWeek(CronExpression.WEEKENDS);
        }
        cronExpression.setType(CronExpression.Type.INTERVAL);
        cronExpression.setHour(receiveStartTime + "-" + receiveEndTime + "/" + String.valueOf(hours));
        cron = cronExpression.toString();
        return cron;
    }
}
