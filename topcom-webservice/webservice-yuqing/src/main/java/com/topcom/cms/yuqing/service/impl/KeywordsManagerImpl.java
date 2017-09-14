package com.topcom.cms.yuqing.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.cms.yuqing.dao.KeywordsDao;
import com.topcom.cms.yuqing.domain.Keywords;
import com.topcom.cms.yuqing.service.KeywordsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "keywordsManager")
@Transactional
public class KeywordsManagerImpl extends GenericManagerImpl<Keywords, Long>
        implements KeywordsManager {
    KeywordsDao keywordsDao;

    @Autowired
    public void setKeywordsDao(KeywordsDao keywordsDao) {
        this.keywordsDao = keywordsDao;
        this.dao = this.keywordsDao;
    }

    @Override
    public Page<Keywords> findByUserId(Pageable pageable, Long userId) {
        return keywordsDao.findByUserId(pageable, userId);
    }

    @Override
    public List<Keywords> findByUserId(Long userId) {
        return keywordsDao.findByUserId(userId);
    }

    @Override
    public List<Keywords> findByUserIdAndType(Long userId, Keywords.Type type) {
        return keywordsDao.findByUserIdAndType(userId, type);
    }

}
