package com.topcom.tjs.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.tjs.dao.TjsOrganDao;
import com.topcom.tjs.domain.TjsOrgan;
import com.topcom.tjs.service.TjsOrganManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
@Service("tjsOrganManager")
@Transactional
public class TjsOrganManagerImpl extends GenericManagerImpl<TjsOrgan, Long> implements TjsOrganManager {

    TjsOrganDao tjsOrganDao;

    @Autowired
    public void setTjsOrganDao(TjsOrganDao tjsOrganDao) {
        this.tjsOrganDao = tjsOrganDao;
        this.dao = tjsOrganDao;
    }
}
