package com.topcom.tjs.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.tjs.dao.TjsEnforcementDao;
import com.topcom.tjs.domain.TjsEnforcement;
import com.topcom.tjs.service.TjsEnforcementManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author lism
 * @date 2018/3/26 0026
 */
@Service("tjsEnforcementManager")
@Transactional
public class TjsEnforcementManagerImpl extends GenericManagerImpl<TjsEnforcement, Long> implements TjsEnforcementManager {

    TjsEnforcementDao tjsEnforcementDao;

    @Autowired
    public void setTjsEnforcementDao(TjsEnforcementDao tjsEnforcementDao) {
        this.tjsEnforcementDao = tjsEnforcementDao;
        this.dao = tjsEnforcementDao;
    }
}
