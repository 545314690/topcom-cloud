package com.topcom.cms.yuqing.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.domain.User;
import com.topcom.cms.exception.BusinessException;
import com.topcom.cms.web.bind.annotation.CurrentUser;
import com.topcom.cms.yuqing.domain.Briefing;
import com.topcom.cms.yuqing.domain.BriefingTask;
import com.topcom.cms.yuqing.service.BriefingTaskManager;
import com.topcom.cms.yuqing.task.briefing.BriefingCreator;
import com.topcom.cms.yuqing.utils.CronExpression;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by maxl on 17-6-12.
 */
@Controller
@RequestMapping("/briefingTask/")
@Api("报告任务接口")
public class BriefingTaskController extends GenericController<BriefingTask, Long, BriefingTaskManager> {

    BriefingTaskManager briefingTaskManager;

    @Autowired
    public void setBriefingTaskManager(BriefingTaskManager briefingTaskManager) {
        this.briefingTaskManager = briefingTaskManager;
        this.manager = briefingTaskManager;
    }

    /**
     * 查询当前登录用户的自定义专题
     *
     * @return 分页数据
     */
    @RequestMapping(value = "/findByType", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public BriefingTask findByUserId(@CurrentUser User user, @RequestParam Briefing.BriefingType briefingType) {
        List<BriefingTask> briefingTasks = briefingTaskManager.findByUserIdAndBriefingType(user.getId(), briefingType);
        if (briefingTasks.size() > 0) {
            BriefingTask briefingTask = briefingTasks.get(0);
            briefingTask.setCronExpression(CronExpression.formCronString(briefingTask.getCron()));
            return briefingTask;
        } else {
            return briefingTaskManager.createDefault(briefingType);
        }
    }

    @Autowired
    BriefingCreator briefingCreator;

    /**
     * testCreate
     */
    @RequestMapping(value = "/testCreate", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object testCreate(@CurrentUser User user, @RequestParam Briefing.BriefingType briefingType,
                             @RequestParam Integer month,
                             @RequestParam(required = false) Integer week
    ) throws Exception {
        BriefingTask briefingTask = findByUserId(user, briefingType);
        Future future = briefingCreator.create(briefingTask,month,week);
        return future.get();
    }
}
