package com.topcom.cms.yuqing.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.cms.yuqing.domain.Briefing;
import com.topcom.cms.yuqing.domain.BriefingTask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by maxl on 17-6-9.
 */
public interface BriefingTaskManager extends
        GenericManager<BriefingTask, Long> {

    List<BriefingTask> findByEnable(boolean enable);

    Page<BriefingTask> findByUserId(Pageable pageable, Long id);

    List<BriefingTask> findByUserIdAndBriefingType(Long userId, Briefing.BriefingType briefingType);

    BriefingTask createDefault(Briefing.BriefingType briefingType);
}
