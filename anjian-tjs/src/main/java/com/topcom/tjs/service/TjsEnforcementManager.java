package com.topcom.tjs.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.tjs.domain.TjsEnforcement;
import com.topcom.tjs.vo.KVPair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

/**
 * @author lism
 * @date 2018/3/26 0026
 */
public interface TjsEnforcementManager extends GenericManager<TjsEnforcement, Long> {
    Page<TjsEnforcement> findByCompanyIdAndZFJCJZSJBetween(Long companyId, Date startDate, Date endDate, Pageable pageable);

    KVPair countByCompanyIdAndDateAndProperty(Long companyId, String startDate, String endDate, String propertyDesc,String property);
}
