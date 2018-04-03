package com.topcom.tjs.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.tjs.domain.TjsEnforcementYear;
import com.topcom.tjs.vo.TBHB;

import java.util.List;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
public interface TjsEnforcementYearManager extends GenericManager<TjsEnforcementYear, Long> {

    List<TBHB> countByMetricName(Integer startYear, Integer endYear, String keyTrades);
}
