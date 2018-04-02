package com.topcom.tjs.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.tjs.dao.TjsEnforcementYearDao;
import com.topcom.tjs.dao.TjsOrganDao;
import com.topcom.tjs.domain.TjsEnforcementYear;
import com.topcom.tjs.domain.TjsOrgan;
import com.topcom.tjs.service.TjsEnforcementYearManager;
import com.topcom.tjs.service.TjsOrganManager;
import com.topcom.tjs.utils.RowMappers;
import com.topcom.tjs.vo.KVPair;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
@Service("tjsEnforcementYearManager")
@Transactional
public class TjsEnforcementYearManagerImpl extends GenericManagerImpl<TjsEnforcementYear, Long> implements TjsEnforcementYearManager {

    private TjsEnforcementYearDao tjsEnforcementYearDao;


    @Autowired
    public void setTjsEnforcementYearDao(TjsEnforcementYearDao tjsEnforcementYearDao) {
        this.tjsEnforcementYearDao = tjsEnforcementYearDao;
        this.dao = tjsEnforcementYearDao;
    }

}
