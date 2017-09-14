package com.topcom.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topcom.cms.base.dao.GenericDao;
import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.cms.dao.OperationLogDao;
import com.topcom.cms.domain.OperationLog;
import com.topcom.cms.service.OperationLogManager;

@Service(value = "operationLogManager")
@Transactional
public class OperationLogServiceImpl extends
        GenericManagerImpl<OperationLog, Long> implements OperationLogManager {

    GenericDao<OperationLog, Long> operationLogDao;

    @Autowired
    public void setOperationLogDao(OperationLogDao operationLogDao) {
        this.operationLogDao = operationLogDao;
        this.dao = this.operationLogDao;
    }

    @Override
    public void deleteOperationLog(Long id) {
        operationLogDao.delete(id);
    }

    @Override
    public OperationLog persist(OperationLog ol) {
        return operationLogDao.save(ol);
    }

}
