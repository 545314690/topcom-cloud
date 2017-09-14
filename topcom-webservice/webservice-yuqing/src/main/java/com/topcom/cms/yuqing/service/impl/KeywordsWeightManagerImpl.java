package com.topcom.cms.yuqing.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.cms.yuqing.dao.KeywordsWeightDao;
import com.topcom.cms.yuqing.domain.Keywords;
import com.topcom.cms.yuqing.domain.KeywordsWeight;
import com.topcom.cms.yuqing.service.KeywordsWeightManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "keywordsWeightManager")
@Transactional
public class KeywordsWeightManagerImpl extends GenericManagerImpl<KeywordsWeight, Long>
        implements KeywordsWeightManager {
    KeywordsWeightDao keywordsWeightDao;

    @Autowired
    public void setKeywordsDao(KeywordsWeightDao keywordsWeightDao) {
        this.keywordsWeightDao = keywordsWeightDao;
        this.dao = this.keywordsWeightDao;
    }

    @Override
    public Page<KeywordsWeight> findByUserId(Pageable pageable, Long userId) {
        return keywordsWeightDao.findByUserId(pageable, userId);
    }

    @Override
    public List<KeywordsWeight> findByUserId(Long userId) {
        return keywordsWeightDao.findByUserId(userId);
    }

    @Override
    public List<KeywordsWeight> findByUserIdAndType(Long userId, KeywordsWeight.Type type) {
        return keywordsWeightDao.findByUserIdAndType(userId, type);
    }

}
