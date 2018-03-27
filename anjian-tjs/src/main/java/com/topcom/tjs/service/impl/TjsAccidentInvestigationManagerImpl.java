package com.topcom.tjs.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.tjs.dao.TjsAccidentInvestigationDao;
import com.topcom.tjs.domain.TjsAccidentInvestigation;
import com.topcom.tjs.service.TjsAccidentInvestigationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author lism
 * @date 2018/3/26 0026
 */
@Service("tjsAccidentInvestigationManager")
@Transactional
public class TjsAccidentInvestigationManagerImpl extends GenericManagerImpl<TjsAccidentInvestigation, Long> implements TjsAccidentInvestigationManager {

    TjsAccidentInvestigationDao tjsAccidentInvestigationDao;

    @Autowired
    public void setTjsAccidentInvestigationDao(TjsAccidentInvestigationDao tjsAccidentInvestigationDao) {
        this.tjsAccidentInvestigationDao = tjsAccidentInvestigationDao;
        this.dao = tjsAccidentInvestigationDao;
    }
}
