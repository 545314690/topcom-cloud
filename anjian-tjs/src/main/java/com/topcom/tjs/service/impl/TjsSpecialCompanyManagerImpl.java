package com.topcom.tjs.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.tjs.dao.TjsSpecialCompanyDao;
import com.topcom.tjs.domain.TjsSpecialCompany;
import com.topcom.tjs.service.TjsSpecialCompanyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
@Service("tjsSpecialCompanyManager")
@Transactional
public class TjsSpecialCompanyManagerImpl extends GenericManagerImpl<TjsSpecialCompany, Long> implements TjsSpecialCompanyManager {

    TjsSpecialCompanyDao tjsSpecialCompanyDao;

    @Autowired
    public void setTjsSpecialCompanyDao(TjsSpecialCompanyDao tjsSpecialCompanyDao) {
        this.tjsSpecialCompanyDao = tjsSpecialCompanyDao;
        this.dao = tjsSpecialCompanyDao;
    }
}
