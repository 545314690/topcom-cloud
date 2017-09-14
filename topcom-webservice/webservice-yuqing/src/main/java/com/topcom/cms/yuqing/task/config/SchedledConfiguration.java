package com.topcom.cms.yuqing.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class SchedledConfiguration {

    @Bean
    public AutoWiringSpringBeanJobFactory autoWiringSpringBeanJobFactory(){
        return new AutoWiringSpringBeanJobFactory();
    }
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setJobFactory(autoWiringSpringBeanJobFactory());
        return scheduler;
    }
}