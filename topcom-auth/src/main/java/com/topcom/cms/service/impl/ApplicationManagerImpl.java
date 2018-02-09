package com.topcom.cms.service.impl;

import com.topcom.cms.base.dao.GenericDao;
import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.cms.dao.ApplicationDao;
import com.topcom.cms.domain.Application;
import com.topcom.cms.service.ApplicationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 应用访问实现类
 *
 * @author lism
 */
@Service(value = "applicationManager")
@Transactional
public class ApplicationManagerImpl extends GenericManagerImpl<Application, Long>
        implements ApplicationManager {

    private ApplicationDao applicationDao;

    @Autowired
    public void setApplicationDao(ApplicationDao applicationDao) {
        this.applicationDao = applicationDao;
        this.dao = this.applicationDao;
    }

    @Override
    public Application findByName(String name) {
        return applicationDao.findByName(name);
    }
}
