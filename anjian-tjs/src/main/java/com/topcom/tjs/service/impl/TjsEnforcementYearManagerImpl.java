package com.topcom.tjs.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.tjs.dao.TjsEnforcementYearDao;
import com.topcom.tjs.domain.TjsEnforcementYear;
import com.topcom.tjs.service.TjsEnforcementYearManager;
import com.topcom.tjs.utils.RowMappers;
import com.topcom.tjs.vo.TBHB;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setTjsEnforcementYearDao(TjsEnforcementYearDao tjsEnforcementYearDao) {
        this.tjsEnforcementYearDao = tjsEnforcementYearDao;
        this.dao = tjsEnforcementYearDao;
    }



    @Override
    public List<TBHB> countByMetricName(Integer startYear, Integer endYear, String keyTrades) {
        //select metricName as name ,year,sum(value) as value ,unit from t_enforcement_year where keyTrades='煤矿' and year BETWEEN 2016 and 2017 group by metricName,year,unit;
        String sql = "select metricName as name ,year as date,sum(value) as value ,unit from t_enforcement_year where year BETWEEN ? and ? ";
        if(StringUtils.isNotBlank(keyTrades)){
            sql+="  and keyTrades='" + keyTrades +"'";

        }
        sql+=" group by name,date,unit;";
        return jdbcTemplate.query(sql,new Object[]{startYear,endYear}, RowMappers.tbhbRowMapper());
    }
}
