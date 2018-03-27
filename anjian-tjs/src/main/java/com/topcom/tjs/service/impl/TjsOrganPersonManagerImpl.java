package com.topcom.tjs.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.tjs.dao.TjsDeathPersonDao;
import com.topcom.tjs.dao.TjsOrganPersonDao;
import com.topcom.tjs.domain.TjsDeathPerson;
import com.topcom.tjs.domain.TjsOrganPerson;
import com.topcom.tjs.service.TjsDeathPersonManager;
import com.topcom.tjs.service.TjsOrganPersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
@Service("tjsOrganPersonManager")
public class TjsOrganPersonManagerImpl extends GenericManagerImpl<TjsOrganPerson, Long> implements TjsOrganPersonManager {

    TjsOrganPersonDao tjsOrganPersonDao;

    @Autowired
    public void setTjsOrganPersonDao(TjsOrganPersonDao tjsOrganPersonDao) {
        this.tjsOrganPersonDao = tjsOrganPersonDao;
        this.dao = tjsOrganPersonDao;
    }
}
