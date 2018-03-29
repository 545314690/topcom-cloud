package com.topcom.tjs.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.tjs.dao.TjsOrganPersonDao;
import com.topcom.tjs.domain.TjsOrganPerson;
import com.topcom.tjs.service.TjsOrganPersonManager;
import com.topcom.tjs.utils.RowMappers;
import com.topcom.tjs.vo.KVPair;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
@Service("tjsOrganPersonManager")
@Transactional
public class TjsOrganPersonManagerImpl extends GenericManagerImpl<TjsOrganPerson, Long> implements TjsOrganPersonManager {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    TjsOrganPersonDao tjsOrganPersonDao;

    @Autowired
    public void setTjsOrganPersonDao(TjsOrganPersonDao tjsOrganPersonDao) {
        this.tjsOrganPersonDao = tjsOrganPersonDao;
        this.dao = tjsOrganPersonDao;
    }
    @Override
    public List<KVPair> countByAreaAndProperty(String province, String city,String property) {
        String sql = null;
        List<KVPair> kvPairs = new ArrayList<>();
        if (StringUtils.isBlank(province)) {
            sql = "select "+ property +" as name, count(1) as value FROM tjs_organ_person  as op INNER JOIN tjs_organ as o on o.id=op.organId group by name ORDER BY VALUE DESC ";
            kvPairs = jdbcTemplate.query(sql, RowMappers.kvPairRowMapper());
        } else if (StringUtils.isBlank(city)) {
            sql = "select  "+ property +"  as name, count(1) as value FROM tjs_organ_person  as op INNER JOIN tjs_organ as o on o.id=op.organId where o.province=? group by name ORDER BY VALUE DESC ";
            kvPairs = jdbcTemplate.query(sql, new String[]{province}, RowMappers.kvPairRowMapper());
        } else {
            sql = "select  "+ property +"  as name, count(1) as value FROM tjs_organ_person  as op INNER JOIN tjs_organ as o on o.id=op.organId where o.province=? and o.city=? group by name ORDER BY VALUE DESC ";
            kvPairs = jdbcTemplate.query(sql, new String[]{province, city}, RowMappers.kvPairRowMapper());
        }
        return kvPairs;
    }
    @Override
    public List<KVPair> countByAreaAndLevel(String province, String city) {
        return this.countByAreaAndProperty(province,city,"level");
    }

    @Override
    public List<KVPair> countByAreaAndGender(String province, String city) {
        return this.countByAreaAndProperty(province,city,"gender");
    }

    @Override
    public List<KVPair> countByAreaAndEducation(String province, String city) {
        return this.countByAreaAndProperty(province,city,"education");
    }

    @Override
    public List<KVPair> countByAreaAndAge(String province, String city) {
        String condition = " case when age between 18 and 24 then '18-24岁'\n" +
                "     when age between 25 and 35 then '25-35岁'\n" +
                "     when age >=36 then '36岁以上'\n" +
                "     else '未知'\n" +
                "end ";
        return this.countByAreaAndProperty(province,city,condition);
    }

    @Override
    public List<KVPair> countByAreaAndCredentials(String province, String city) {
       String condition = " case when number is not null then '有证' else '无证' end ";
        return this.countByAreaAndProperty(province,city,condition);
    }

    @Override
    public Page<TjsOrganPerson> findByOrganId(Long organId,  Pageable pageable) {
        return tjsOrganPersonDao.findByOrganId(organId, pageable);
    }
}
