package com.topcom.tjs.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.tjs.dao.TjsEnforcementDao;
import com.topcom.tjs.domain.TjsEnforcement;
import com.topcom.tjs.service.TjsEnforcementManager;
import com.topcom.tjs.utils.RowMappers;
import com.topcom.tjs.vo.KVPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * @author lism
 * @date 2018/3/26 0026
 */
@Service("tjsEnforcementManager")
@Transactional
public class TjsEnforcementManagerImpl extends GenericManagerImpl<TjsEnforcement, Long> implements TjsEnforcementManager {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    TjsEnforcementDao tjsEnforcementDao;

    @Autowired
    public void setTjsEnforcementDao(TjsEnforcementDao tjsEnforcementDao) {
        this.tjsEnforcementDao = tjsEnforcementDao;
        this.dao = tjsEnforcementDao;
    }

    @Override
    public Page<TjsEnforcement> findByCompanyIdAndZFJCJZSJBetween(Long companyId, Date startDate, Date endDate, Pageable pageable) {
        return tjsEnforcementDao.findByCompanyIdAndZFJCJZSJBetween(companyId, startDate, endDate, pageable);
    }

    @Override
    public KVPair countByCompanyIdAndDateAndProperty(Long companyId, String startDate, String endDate, String propertyDesc,String property) {
        String sql = "select '"+propertyDesc+"' as name, sum(" +
                property+") as value FROM t_enforcement  as op INNER JOIN tjs_special_company as o on o.id=op.companyId where op.companyId=" +
                companyId+" and ZFJCJZSJ BETWEEN '" +
                startDate+ "' and '" +
                endDate +"'";
        return jdbcTemplate.queryForObject(sql, RowMappers.kvPairRowMapper());
    }
}
