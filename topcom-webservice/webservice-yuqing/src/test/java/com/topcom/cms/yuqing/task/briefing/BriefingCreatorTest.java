package com.topcom.cms.yuqing.task.briefing;

import com.topcom.WebserviceYuqingApplication;
import com.topcom.cms.base.model.BaseEntityModel;
import com.topcom.cms.base.service.GenericManager;
import com.topcom.cms.base.service.GenericManagerTestCase;
import com.topcom.cms.yuqing.domain.Briefing;
import com.topcom.cms.yuqing.domain.BriefingTask;
import com.topcom.cms.yuqing.service.BriefingTaskManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.Serializable;

import static org.junit.Assert.*;

/**
 * Created by lism on 17-6-24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebserviceYuqingApplication.class)
@WebAppConfiguration
public class BriefingCreatorTest extends GenericManagerTestCase<Long, BriefingTask, BriefingTaskManager>{
    public BriefingCreatorTest(Class<BriefingTask> persistentClass) {
        super(persistentClass);
    }

//    @Autowired
//    BriefingTaskManager briefingTaskManager;
//    @Autowired
//    BriefingCreator briefingCreator;
//    @Test
//    public void createWeekly() throws Exception {
//        briefingTaskManager.findByUserIdAndBriefingType(1L, Briefing.BriefingType.MONTHLY);
//    }

    @Test
    public void createMonthly() throws Exception {
    }

}