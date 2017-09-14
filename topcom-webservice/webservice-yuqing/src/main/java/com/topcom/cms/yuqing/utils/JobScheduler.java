package com.topcom.cms.yuqing.utils;

import com.topcom.cms.common.utils.LogUtil;
import org.quartz.*;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by lism on 17-6-6.
 */
@Component
public class JobScheduler {
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    private Scheduler scheduler;

    @PostConstruct
    public void initScheduler() {
        scheduler = schedulerFactoryBean.getScheduler();
    }

    public Date scheduleJob(Class<? extends Job> jobClass, String name, String cornExpression, Map<String, Object> jobDataMap) {
        return scheduleJob(jobClass, name, jobClass.getName().toString(), cornExpression, jobDataMap);

    }

    public Date scheduleJob(Class<? extends Job> jobClass, String name, String group, String cornExpression, Map<String, Object> jobDataMap) {

        Date date = null;
        // 获取trigger
        TriggerKey triggerKey = TriggerKey.triggerKey(name, group);
        try {
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger != null) {//如果任务存在，更新
                date = rescheduleJob(jobClass, name, group, cornExpression, jobDataMap);
            } else {//如果任务不存在，创建
                JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(name, group).build();
                jobDetail.getJobDataMap().putAll(jobDataMap);
                // 表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cornExpression);
                scheduleBuilder.inTimeZone(TimeZone.getTimeZone("GMT+08"));
                // 按新的表达式构建一个新的trigger
                trigger = TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(scheduleBuilder).build();
                // 启动任务
                date = scheduler.scheduleJob(jobDetail, trigger);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        LogUtil.logger.info("group@" + group + "@name" + name + "-->任务触发时间:" + date.toString());
        return date;

    }

    public boolean deleteJob(Class<? extends Job> jobClass, String name) {
        return deleteJob(jobClass, name, jobClass.getName().toString());
    }

    public boolean deleteJob(Class<? extends Job> jobClass, String name, String group) {
        JobDetail jobDetail = JobBuilder
                .newJob(jobClass)
                .withIdentity(name, group).build();
        try {
            return scheduler.deleteJob(jobDetail.getKey());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Date rescheduleJob(Class<? extends Job> jobClass, String name, String group, String cornExpression, Map<String, Object> jobDataMap) {
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(name, group).build();
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(name, group);
            // 获取trigger
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cornExpression);
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            jobDetail.getJobDataMap().putAll(jobDataMap);
            // 按新的trigger重新设置job执行
            Date date = scheduler.rescheduleJob(triggerKey, trigger);
            return date;
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Date rescheduleJob(Class<? extends Job> jobClass, String name, String cornExpression, Map<String, Object> jobDataMap) {
        return rescheduleJob(jobClass, name, jobClass.getName().toString(), cornExpression, jobDataMap);
    }
}
