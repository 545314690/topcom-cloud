package com.topcom.cms.yuqing.aop;

import com.topcom.cms.common.utils.LogUtil;
import com.topcom.cms.perm.exception.UnLoginException;
import com.topcom.cms.yuqing.domain.CustomSubject;
import com.topcom.cms.yuqing.service.CustomSubjectManager;
import com.topcom.cms.yuqing.task.briefing.BriefingCreator;
import com.topcom.cms.yuqing.task.briefing.BriefingCreatorImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by maxl on 17-6-5.
 */

@Aspect
@Component
public class SubjectAspect {

//    @Autowired
//    BriefingCreator briefingCreator;
//    CustomSubjectManager customSubjectManager;
//
//    @Autowired
//    public void setCustomSubjectManager(CustomSubjectManager customSubjectManager) {
//        this.customSubjectManager = customSubjectManager;
//    }
//
//    @Pointcut("execution(public * com.topcom.cms.yuqing.controller.CustomSubjectController.create(com.topcom.cms.yuqing.domain.CustomSubject))")
//    public void createSubject() {
//    }
//
//    @Pointcut("execution(public * com.topcom.cms.yuqing.controller.CustomSubjectController.updateReport(String))")
//    public void updateSubject() {
//    }
//
//    @Around("createSubject()")
//    public void doAfter(ProceedingJoinPoint joinPoint) throws Throwable {
//        Object[] args = joinPoint.getArgs();
//        if (args.length > 0) {
//            CustomSubject customSubject = (CustomSubject) joinPoint.proceed(args);
//            //调用生成专报方法
//            briefingCreator.createSpecial(customSubject);
//        }
//
//    }
//
//    @Before("updateSubject()")
//    public void updateBefore(JoinPoint joinPoint) {
//        LogUtil.logger.info("----------------专报更新中--------------------");
//    }
}
