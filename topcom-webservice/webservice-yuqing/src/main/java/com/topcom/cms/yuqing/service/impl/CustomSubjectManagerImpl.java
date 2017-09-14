package com.topcom.cms.yuqing.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.cms.yuqing.dao.CustomSubjectDao;
import com.topcom.cms.yuqing.service.CustomSubjectManager;
import com.topcom.cms.yuqing.domain.CustomSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 自定义专题业务层
 *
 * @author <a href="mailto:545314690@qq.om">lisenmiao</a>
 * @date 2014年9月12日上午10:15:41
 */
@Service(value = "customSubjectManager")
@Transactional
@CacheConfig(cacheNames = "customSubject")
public class CustomSubjectManagerImpl extends
        GenericManagerImpl<CustomSubject, Long> implements CustomSubjectManager {

    CustomSubjectDao customSubjectDao;

    @Autowired
    public void setCustomSubjectDao(CustomSubjectDao customSubjectDao) {
        this.customSubjectDao = customSubjectDao;
        this.dao = this.customSubjectDao;
    }

    @Override
    public Page<CustomSubject> findByUserId(Pageable pageable, Long userId) {
        return customSubjectDao.findByUserId(pageable, userId);
    }

    @Override
    public CustomSubject findByUserIdAndName(Long id, String name) {
        return customSubjectDao.findByUserIdAndName(id, name);
    }

    @Override
    public List<CustomSubject> findByEnableWarning(boolean enable) {
        return customSubjectDao.findByEnableWarning(enable);
    }

    @Override
    public boolean checkEnableWarning(CustomSubject subject) {
        return subject.isEnableWarning();
    }

    @Override
    public int updateState(Long id, CustomSubject.State state) {
        return customSubjectDao.updateState(id, state);
    }

    @Override
    public Page<CustomSubject> findByUserIdAndNameLike(Pageable pageable, Long id, String subjectName) {
        subjectName = "%"+subjectName+"%";
        return customSubjectDao.findByUserIdAndNameLike(pageable,id,subjectName);
    }


    @Override
    public CustomSubject save(CustomSubject entity) {

        return super.save(entity);
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }
}
