package com.topcom.cms.yuqing.domain;

import com.topcom.cms.common.utils.LogUtil;
import com.topcom.cms.yuqing.utils.CronExpression;

import javax.persistence.*;
import java.util.List;

/**
 * Created by maxl on 17-6-12.
 */
@Entity
@Table(name = "t_briefing_task")
public class BriefingTask extends BriefingBaseTask {

    /**
     * 期号
     */
    @Transient
    private String issue;
    /**
     * 预警联系人
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "t_briefing_task_contact",
            joinColumns = @JoinColumn(name = "briefingTaskId"),
            inverseJoinColumns = @JoinColumn(name = "contactId")
    )
    private List<Contact> contacts;

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public BriefingTask() {
    }

    public BriefingTask(Briefing.BriefingType briefingType) {
        this.setBriefingType(briefingType);
    }

    /**
     * ，通过BriefingType构建默认的报告
     *
     * @return
     */
    public BriefingTask createDefault() {
        if (this.getBriefingType() != null) {
            switch (this.getBriefingType()) {
                case DAILY:
                    cronExpression.setHour("18");
                    break;
                case WEEKLY:
                    cronExpression.setWeek("1");
                    cronExpression.setHour("18");
                    break;
                case MONTHLY:
                    cronExpression.setDay("L");
                    cronExpression.setHour("18");
                    break;
                default:
                    break;
            }
        }
        this.setCron(cronExpression.toString());
        return this;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public static void main(String[] args) {
        BriefingTask task = new BriefingTask(Briefing.BriefingType.DAILY);
        task.createDefault();
        task.getCronExpression().setAtWeekends(true);
        CronExpression expression = task.getCronExpression();
        expression = CronExpression.formCronString(task.getCron());
        LogUtil.logger.info(task);
        LogUtil.logger.info(expression);
    }
}
