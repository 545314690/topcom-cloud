package com.topcom.tjs.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.tjs.dao.TjsAccidentDao;
import com.topcom.tjs.domain.TjsAccident;
import com.topcom.tjs.service.TjsAccidentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
@Service("tjsAccidentManager")
@Transactional
public class TjsAccidentManagerImpl extends GenericManagerImpl<TjsAccident, Long> implements TjsAccidentManager {

    TjsAccidentDao tjsAccidentDao;

    @Autowired
    public void setTjsAccidentDao(TjsAccidentDao tjsAccidentDao) {
        this.tjsAccidentDao = tjsAccidentDao;
        this.dao = tjsAccidentDao;
    }
}
