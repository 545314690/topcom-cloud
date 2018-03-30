package com.topcom.tjs.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.tjs.domain.TjsAccident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
public interface TjsAccidentManager extends GenericManager<TjsAccident, Long> {
    Page<TjsAccident> findByCompanyIdAndHappenedTimeBetween(Long accidentId, Date startDate, Date endDate, Pageable pageable);
}
