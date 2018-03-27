package com.topcom.tjs.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.tjs.dao.TjsOrganDao;
import com.topcom.tjs.domain.TjsOrgan;
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
@Service("tjsOrganManager")
@Transactional
public class TjsOrganManagerImpl extends GenericManagerImpl<TjsOrgan, Long> implements TjsOrganManager {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private TjsOrganDao tjsOrganDao;

    @Autowired
    public void setTjsOrganDao(TjsOrganDao tjsOrganDao) {
        this.tjsOrganDao = tjsOrganDao;
        this.dao = tjsOrganDao;
    }

    @Override
    public List<KVPair> countByArea(String province, String city) {
        String sql = null;
        List<KVPair> kvPairs = new ArrayList<>();
        if (StringUtils.isBlank(province)) {
            sql = "select province as name, count(1) as value FROM tjs_organ  group by province ORDER BY VALUE DESC ";
            kvPairs = jdbcTemplate.query(sql, RowMappers.kvPairRowMapper());
        } else if (StringUtils.isBlank(city)) {
            sql = "select city as name, count(1) as value FROM tjs_organ  WHERE province=? group by province,city ORDER BY VALUE DESC";
            kvPairs = jdbcTemplate.query(sql, new String[]{province}, RowMappers.kvPairRowMapper());
        } else {
            sql = "select county as name, count(1) as value FROM tjs_organ  WHERE province=? and city=? group by province, city,county ORDER BY VALUE DESC";
            kvPairs = jdbcTemplate.query(sql, new String[]{province, city}, RowMappers.kvPairRowMapper());
        }
        return kvPairs;
    }
}
