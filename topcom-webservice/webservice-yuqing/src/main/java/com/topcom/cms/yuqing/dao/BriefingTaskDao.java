package com.topcom.cms.yuqing.dao;

import com.topcom.cms.base.dao.GenericDao;
import com.topcom.cms.yuqing.domain.Briefing;
import com.topcom.cms.yuqing.domain.BriefingTask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by maxl on 17-6-9.
 */
public interface BriefingTaskDao extends GenericDao<BriefingTask, Long> {

    List<BriefingTask> findByEnable(boolean enable);

    Page<BriefingTask> findByUserId(Pageable pageable, Long userId);

    List<BriefingTask> findByUserIdAndBriefingType(Long userId, Briefing.BriefingType briefingType);
}
