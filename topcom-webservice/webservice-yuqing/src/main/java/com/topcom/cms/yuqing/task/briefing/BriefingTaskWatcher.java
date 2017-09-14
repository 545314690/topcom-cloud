package com.topcom.cms.yuqing.task.briefing;

import com.topcom.cms.common.utils.LogUtil;
import com.topcom.cms.exception.BusinessException;
import com.topcom.cms.yuqing.domain.BriefingTask;
import com.topcom.cms.yuqing.service.BriefingTaskManager;
import com.topcom.cms.yuqing.utils.JobScheduler;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by maxl on 17-6-9.
 */
@Component
@EnableScheduling
@Aspect
public class BriefingTaskWatcher {





    @Autowired
    JobScheduler jobScheduler;

    @Autowired
    private BriefingTaskManager briefingTaskManager;



    @Pointcut(value = "execution(* com.topcom.cms.yuqing.service.BriefingTaskManager.delete(..)))")
    public void deleteBriefingTask() {
    }

    @Pointcut(value = "execution(* com.topcom.cms.yuqing.service.BriefingTaskManager.save(..)))")
    public void updateBriefingTask() {
    }


    @AfterReturning("deleteBriefingTask()")
    public void doAroundDeleteWarning(JoinPoint joinPoint) throws BusinessException {
        Object[] args = joinPoint.getArgs();
        if (args.length == 1) {
            Long subjectId = (Long) args[0];
            //删除任务
            deleteJob(String.valueOf(subjectId));
            LogUtil.logger.info("删除任务：" + subjectId + "成功");
        }
    }

    @Around("updateBriefingTask()")
    public Object doAroundUpdate(ProceedingJoinPoint joinPoint) throws BusinessException {
        Object[] args = joinPoint.getArgs();
        if (args.length == 1) {
            try {
                Object proceed = joinPoint.proceed(args);
                final BriefingTask briefingTask = (BriefingTask) proceed;
                if (briefingTask != null) {
                    if (briefingTask.isEnable() == true) {//如果是开启
                        scheduleJob(briefingTask);
                        LogUtil.logger.info("更新任务：" + briefingTask.getBriefingType() + "成功");
                    } else {//如果是关闭
                        deleteJob(String.valueOf(briefingTask.getId()));
                    }
                }
                return briefingTask;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                throw new BusinessException("更新失败");
            }
        }
        return null;
    }

    private boolean deleteJob(String id) {
        return jobScheduler.deleteJob(BriefingJob.class, id);
    }

    @PostConstruct
    public void loadTask() {

        List<BriefingTask> briefingTaskList = briefingTaskManager.findByEnable(true);
        // 任务调度
        final long count = briefingTaskList.stream().map(
                subject -> {
                    return scheduleJob(subject);
                }).count();
        LogUtil.logger.info("初始化舆情报告Job数量：" + count);
    }
    private Date scheduleJob(BriefingTask briefingTask) {
        if (briefingTask == null || briefingTask.getCron() == null) {
            return null;
        }
        Map<String, Object> jobDataMap = new HashMap<>();
        jobDataMap.put("briefingTaskId", briefingTask.getId());
        return jobScheduler.scheduleJob(BriefingJob.class, String.valueOf(briefingTask.getId()), briefingTask.getCron(), jobDataMap);
    }
}
