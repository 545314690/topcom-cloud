package com.topcom.bi.service.impl;

import com.topcom.bi.dao.InterfaceDao;
import com.topcom.bi.domain.Interface;
import com.topcom.bi.service.InterfaceService;
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
public class InterfaceServiceImpl extends BaseServiceImpl<Interface, String> implements InterfaceService {
    private InterfaceDao interfaceDao;

    @Autowired
    public void setInterfaceDao(InterfaceDao interfaceDao) {
        this.interfaceDao = interfaceDao;
        this.baseDao = interfaceDao;
    }
    @Override
    public Page<Interface> findByNameLike(String name, Pageable pageable) {
        return interfaceDao.findByNameLike(name, pageable) ;
    }
}
