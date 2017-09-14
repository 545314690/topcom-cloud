package com.topcom.cms.yuqing.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.cms.yuqing.dao.CollectionDao;
import com.topcom.cms.yuqing.domain.Collection;
import com.topcom.cms.yuqing.domain.MediaType;
import com.topcom.cms.yuqing.service.CollectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 收藏业务层实现
 *
 * @author <a href="mailto:545314690@qq.om">lisenmiao</a>
 * @date 2014年10月11日下午3:44:17
 */
@Service(value = "collectionManager")
@Transactional
public class CollectionManagerImpl extends GenericManagerImpl<Collection, Long>
        implements CollectionManager {

    CollectionDao collectionDao;

    @Autowired
    public void setCollectionDao(CollectionDao collectionDao) {
        this.collectionDao = collectionDao;
        this.dao = this.collectionDao;
    }

    @Override
    public Page<Collection> findByType(Pageable pageable, MediaType type, String keywords) {
        return this.collectionDao.findByType(pageable, type, keywords);
    }

    @Override
    public Collection findByOId(String oId) {
        return this.collectionDao.findByOId(oId);
    }

    @Override
    public Page<Collection> findByUserId(Pageable pageable, Long id) {
        return this.collectionDao.findByUserId(pageable, id);
    }

}
