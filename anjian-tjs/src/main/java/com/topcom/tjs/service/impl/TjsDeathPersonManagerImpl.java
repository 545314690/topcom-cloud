package com.topcom.tjs.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.tjs.dao.TjsDeathPersonDao;
import com.topcom.tjs.domain.TjsDeathPerson;
import com.topcom.tjs.service.TjsDeathPersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
@Service("tjsDeathPersonCompanyManager")
@Transactional
public class TjsDeathPersonManagerImpl extends GenericManagerImpl<TjsDeathPerson, Long> implements TjsDeathPersonManager {

    TjsDeathPersonDao tjsDeathPersonDao;

    @Autowired
    public void setTjsDeathPersonDao(TjsDeathPersonDao tjsDeathPersonDao) {
        this.tjsDeathPersonDao = tjsDeathPersonDao;
        this.dao = tjsDeathPersonDao;
    }
}
