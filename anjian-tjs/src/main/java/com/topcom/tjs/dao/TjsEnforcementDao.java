package com.topcom.tjs.dao;

import com.topcom.cms.base.dao.GenericDao;
import com.topcom.tjs.domain.TjsEnforcement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
public interface TjsEnforcementDao extends GenericDao<TjsEnforcement,Long> {
    Page<TjsEnforcement> findByCompanyIdAndZFJCJZSJBetween(Long companyId, Date startDate, Date endDate, Pageable pageable);
}
