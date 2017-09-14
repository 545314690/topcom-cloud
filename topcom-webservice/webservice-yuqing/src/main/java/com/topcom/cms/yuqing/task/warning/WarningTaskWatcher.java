package com.topcom.cms.yuqing.task.warning;

import com.topcom.cms.common.utils.LogUtil;
import com.topcom.cms.exception.BusinessException;
import com.topcom.cms.yuqing.domain.CustomSubject;
import com.topcom.cms.yuqing.service.CustomSubjectManager;
import com.topcom.cms.yuqing.task.briefing.BriefingCreator;
import com.topcom.cms.yuqing.task.kafka.KafkaSender;
import com.topcom.cms.yuqing.utils.JobScheduler;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@EnableScheduling
@Aspect
public class WarningTaskWatcher {

    /**
     * 发送到kafka 的topic
     */
    private final String SUBJECT_TOPIC = "topic_subject";
    @Autowired
    KafkaSender kafkaSender;

    @Autowired
    BriefingCreator briefingCreator;

    @Autowired
    JobScheduler jobScheduler;

    /**
     * 发送到kakfa，供爬虫使用
     *
     * @param subject
     */
    private void sendToKafka(CustomSubject subject) {
        kafkaSender.getProducer().send(new ProducerRecord<String, String>(SUBJECT_TOPIC, net.sf.json.JSONObject.fromObject(subject).toString()));
    }

    @Autowired
    private CustomSubjectManager subjectManager;


    @Pointcut(value = "execution(* com.topcom.cms.yuqing.controller.CustomSubjectController.delete(java.lang.Long)))")
    public void deleteSubject() {
    }

    @Pointcut("execution(public * com.topcom.cms.yuqing.controller.CustomSubjectController.create(com.topcom.cms.yuqing.domain.CustomSubject))")
    public void addSubject() {
    }

    @Pointcut("execution(* com.topcom.cms.yuqing.controller.CustomSubjectController.update(java.lang.Long,com.topcom.cms.yuqing.domain.CustomSubject))")
    public void updateSubject() {
    }
    @Pointcut("addSubject() || updateSubject()")
    private void saveOrUpdateMethod() {
    }


    @Around("deleteSubject()")
    public void doAroundDeleteWarning(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args.length == 1) {
            Long subjectId = (Long) args[0];
            /**
             * 为了给kafak发送消息，查询一次，并标记为删除
             */
            CustomSubject subject = subjectManager.findById(subjectId);

            /**
             * 执行删除
             */
            joinPoint.proceed(args);

            subject.setDeleted(true);

            //发送到kafka
            sendToKafka(subject);

            //删除任务
            deleteJob(String.valueOf(subjectId));
            /**
             * 发送结束
             */
            LogUtil.logger.info("删除任务：" + subjectId + "成功");

        }
    }


    @Around("saveOrUpdateMethod()")
    public Object doAroundUpdate(ProceedingJoinPoint joinPoint) throws BusinessException {
        Object[] args = joinPoint.getArgs();
        if (args.length >= 1) {
            try {
                Object proceed = joinPoint.proceed(args);
                final CustomSubject subject = (CustomSubject) proceed;
                if (subject != null) {
                    /**
                     * 如果是调用新增方法（1个参数，update是两个参数），调用生成专报方法
                     */
                    if (args.length == 1) {
                        briefingCreator.createSpecial(subject);
                    }

                    /**
                     * 预警任务调度
                     */
                    if (subject.isEnableWarning() == true) {//如果是开启
                        scheduleJob(subject);
                        LogUtil.logger.info("更新任务：" + subject.getName() + "成功");
                    } else {//如果是关闭
                        deleteJob(String.valueOf(subject.getId()));
                    }

                    /**
                     *发送到kafka
                     */
                    sendToKafka(subject);
                }
                return subject;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                throw new BusinessException("更新失败");
            }
        }
        return null;
    }

    private Date scheduleJob(CustomSubject subject) {
        if (subject == null || subject.getWarning() == null || subject.getWarning().getCron() == null) {
            return null;
        }
        Map<String, Object> jobDataMap = new HashMap<>();
        jobDataMap.put("subjectId", subject.getId());
        return jobScheduler.scheduleJob(WarningJob.class, String.valueOf(subject.getId()), subject.getWarning().getCron(), jobDataMap);

    }

    private boolean deleteJob(String id) {
        return jobScheduler.deleteJob(WarningJob.class, id);
    }

    @PreDestroy
    public void preDestory() {
        LogUtil.logger.info(this.getClass().getName() + "destory");
    }

    /**
     * 初始化调度任务
     */
    @PostConstruct
    public void loadTask() {
        List<CustomSubject> subjectList = subjectManager.findByEnableWarning(true);
        // 任务调度
        final long count = subjectList.stream().map(
                subject -> {
                    /**
                     * 逐条发送到kafka
                     */
                    sendToKafka(subject);
                    return scheduleJob(subject);
                }).count();
        LogUtil.logger.info("初始化WarningJob数量：" + count);
    }

}