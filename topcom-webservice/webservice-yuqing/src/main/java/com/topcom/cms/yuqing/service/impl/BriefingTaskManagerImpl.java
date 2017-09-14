package com.topcom.cms.yuqing.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.cms.yuqing.dao.BriefingTaskDao;
import com.topcom.cms.yuqing.domain.Briefing;
import com.topcom.cms.yuqing.domain.BriefingTask;
import com.topcom.cms.yuqing.service.BriefingTaskManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by maxl on 17-6-9.
 */
@Service(value = "briefingTaskManager")
@Transactional
@CacheConfig(cacheNames = "briefingTask")
public class BriefingTaskManagerImpl  extends
        GenericManagerImpl<BriefingTask, Long> implements BriefingTaskManager {



    BriefingTaskDao briefingTaskDao;

    @Autowired
    public void setBriefingTaskDao(BriefingTaskDao briefingTaskDao) {
        this.briefingTaskDao = briefingTaskDao;
        this.dao = briefingTaskDao;
    }

    @Override
    public List<BriefingTask> findByEnable(boolean enable) {
        return briefingTaskDao.findByEnable(enable);
    }

    @Override
    public Page<BriefingTask> findByUserId(Pageable pageable, Long userId) {
        return briefingTaskDao.findByUserId(pageable, userId);
    }

    @Override
    public List<BriefingTask> findByUserIdAndBriefingType(Long userId, Briefing.BriefingType briefingType) {
        return briefingTaskDao.findByUserIdAndBriefingType(userId, briefingType);
    }

    /**
     * 重写父类，需要aop切面
     * @param entity
     *            entity for saving
     * @return
     */
    @Override
    public BriefingTask save(BriefingTask entity) {
        return super.save(entity);
    }

    @Override
    public BriefingTask createDefault(Briefing.BriefingType briefingType) {
        BriefingTask briefingTask = new BriefingTask(briefingType).createDefault();
        return briefingTaskDao.save(briefingTask);
    }
}
