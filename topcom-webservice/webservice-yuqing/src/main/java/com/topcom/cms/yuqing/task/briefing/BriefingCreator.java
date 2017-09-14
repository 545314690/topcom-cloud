package com.topcom.cms.yuqing.task.briefing;

import com.topcom.cms.exception.BusinessException;
import com.topcom.cms.yuqing.domain.Briefing;
import com.topcom.cms.yuqing.domain.BriefingTask;
import com.topcom.cms.yuqing.domain.CustomSubject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public interface BriefingCreator {

    //日报
    Future createDaily() throws BusinessException;

    //周报
    Future createWeekly(BriefingTask briefingTask) throws BusinessException;

    //月报
    Future createMonthly(BriefingTask briefingTask) throws BusinessException;

    //季报
    Future createQuarterly() throws BusinessException;

    //半年报
    Future createAsemiAnnualReport() throws BusinessException;

    //年报
    Future createAnnualReport() throws BusinessException;

    default Future create(BriefingTask task) throws BusinessException, ExecutionException, InterruptedException {
        Future future = null;
        if (Briefing.BriefingType.MONTHLY.equals(task.getBriefingType())) {
            future = this.createMonthly(task);
        } else if (Briefing.BriefingType.WEEKLY.equals(task.getBriefingType())) {
            future = this.createWeekly(task);
        }
        return future;
    }


    /**
     * 根据专题生产专报
     *
     * @param customSubject
     * @return
     */

    Future createSpecial(CustomSubject customSubject) throws Exception;


    /**
     * 更新专报
     *
     * @param id
     * @return
     * @throws Exception
     */
    Future updateSpecial(String id) throws Exception;

    Future create(BriefingTask briefingTask, Integer month, Integer week) throws Exception;
}