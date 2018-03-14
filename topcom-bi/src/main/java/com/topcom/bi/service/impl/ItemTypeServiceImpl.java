package com.topcom.bi.service.impl;

import com.topcom.bi.dao.ItemTypeDao;
import com.topcom.bi.domain.ItemType;
import com.topcom.bi.service.ItemTypeService;
import com.topcom.cms.mongo.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lism on 17-10-31.
 *
 * @author lism
 */
@Service
@Transactional
public class ItemTypeServiceImpl extends BaseServiceImpl<ItemType, String> implements ItemTypeService {
    private ItemTypeDao itemTypeDao;

    @Autowired
    public void setItemTypeDao(ItemTypeDao itemTypeDao) {
        this.itemTypeDao = itemTypeDao;
        this.baseDao = itemTypeDao;
    }
    @Override
    public Page<ItemType> findByNameLike(String name, Pageable pageable) {
        return itemTypeDao.findByNameLike(name, pageable) ;
    }
}
