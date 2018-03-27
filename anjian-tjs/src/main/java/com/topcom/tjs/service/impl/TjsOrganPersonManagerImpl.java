package com.topcom.tjs.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.tjs.dao.TjsOrganPersonDao;
import com.topcom.tjs.domain.TjsOrganPerson;
import com.topcom.tjs.service.TjsOrganPersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
@Service("tjsOrganPersonManager")
@Transactional
public class TjsOrganPersonManagerImpl extends GenericManagerImpl<TjsOrganPerson, Long> implements TjsOrganPersonManager {

    TjsOrganPersonDao tjsOrganPersonDao;

    @Autowired
    public void setTjsOrganPersonDao(TjsOrganPersonDao tjsOrganPersonDao) {
        this.tjsOrganPersonDao = tjsOrganPersonDao;
        this.dao = tjsOrganPersonDao;
    }
}
