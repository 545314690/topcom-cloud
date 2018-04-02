package com.topcom.tjs.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.tjs.dao.TjsEnforcementDao;
import com.topcom.tjs.domain.TjsEnforcement;
import com.topcom.tjs.service.TjsEnforcementManager;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public KVPair countByCompanyIdAndDateAndProperty(Long companyId, String startDate, String endDate, String propertyDesc, String property) {
        String sql = "select '" + propertyDesc + "' as name, sum(" +
                property + ") as value FROM t_enforcement  as op INNER JOIN tjs_special_company as o on o.id=op.companyId where op.companyId=" +
                companyId + " and ZFJCJZSJ BETWEEN '" +
                startDate + "' and '" +
                endDate + "'";
        return jdbcTemplate.queryForObject(sql, RowMappers.kvPairRowMapper());
    }

    @Override
    public List<KVPair> countByDateAndAreaAndIndustryTypeAndProperty(String startDate, String endDate, String industryType, String province, String city, String companyId, String property) {
        String sql = "select " + property + " as name, count(1) as value FROM t_enforcement as t1 INNER JOIN tjs_special_company as t2 on t1.companyId=t2.id ";
        sql = connectSqlString(startDate, endDate, industryType, province, city, companyId, sql);
        sql += " group by name ORDER BY VALUE DESC ";
        return jdbcTemplate.query(sql, RowMappers.kvPairRowMapper());
    }

    private String connectSqlString(String startDate, String endDate, String industryType, String province, String city, String companyId, String sql) {
        boolean flag = false;
        if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
            sql += " WHERE ZFJCJZSJ BETWEEN '" + startDate + "' AND '" + endDate + "' ";
            flag = true;
        }
        if (StringUtils.isNotBlank(industryType)) {
            if (flag) {
                sql += " AND ";
            } else {
                sql += " WHERE ";
                flag = true;
            }
            sql += " t2.industryType= '" + industryType + "'";
        }
        if (StringUtils.isNotBlank(province)) {
            if (flag) {
                sql += " AND ";
            } else {
                sql += " WHERE ";
                flag = true;
            }
            sql += " t2.province='" + province + "'";
        }
        if (StringUtils.isNotBlank(city)) {
            if (flag) {
                sql += " AND ";
            } else {
                sql += " WHERE ";
            }
            sql += " t2.city='" + city + "'";
        }
        if (StringUtils.isNotBlank(companyId)) {
            if (flag) {
                sql += " AND ";
            } else {
                sql += " WHERE ";
            }
            sql += " t2.id='" + companyId + "'";
        }
        return sql;
    }

    @Override
    public List<KVPair> countByDateAndAreaAndIndustryTypeAndCategory(String startDate, String endDate, String industryType, String province, String city, String companyId) {
        return this.countByDateAndAreaAndIndustryTypeAndProperty(startDate, endDate, industryType, province, city, companyId, "JFJCLB");
    }

    @Override
    public List<KVPair> countByDateAndAreaAndIndustryTypeAndRandomCheck(String startDate, String endDate, String industryType, String province, String city, String companyId) {
        return this.countByDateAndAreaAndIndustryTypeAndProperty(startDate, endDate, industryType, province, city, companyId, "SFWSJCQJCDW");
    }

    @Override
    public List<KVPair> countByDateAndAreaAndIndustryTypeAndReCheck(String startDate, String endDate, String industryType, String province, String city, String companyId) {
        return this.countByDateAndAreaAndIndustryTypeAndProperty(startDate, endDate, industryType, province, city, companyId, "SFZGFC");
    }

    @Override
    public List<KVPair> countByDateAndAreaAndIndustryTypeAndHealth(String startDate, String endDate, String industryType, String province, String city, String companyId) {
        return this.countByDateAndAreaAndIndustryTypeAndProperty(startDate, endDate, industryType, province, city, companyId, "SFHZYWSZFJC");
    }

    @Override
    public List<KVPair> countByDateAndAreaAndIndustryTypeAndReport(String startDate, String endDate, String industryType, String province, String city, String companyId) {
        return this.countByDateAndAreaAndIndustryTypeAndProperty(startDate, endDate, industryType, province, city, companyId, "SFJBHSZFJC");
    }

    @Override
    public Map<String, Object> sumByDateAndAreaAndIndustryTypeAndFine(String startDate, String endDate, String industryType, String province, String city, String companyId) {
        List<KVPair> kvList = new ArrayList<>();
        kvList.add(new KVPair("罚款额（万元）", "FKE"));
        kvList.add(new KVPair("实际收缴罚款（万元）", "SJSJFK"));
        kvList.add(new KVPair("查处一般事故隐患（项）", "CCYBSGYHX"));
        kvList.add(new KVPair("查处重大事故隐患（项）", "CCZDSGYHX"));
        kvList.add(new KVPair("查处安全生产违法违规行为（项）", "CCAQSCWFWGXWX"));
        kvList.add(new KVPair("已整改安全生产违法违规行为（项）", "YZGAQSCWFWGXW"));
        kvList.add(new KVPair("已整改一般事故隐患（项）", "YZGYBSGYH"));
        kvList.add(new KVPair("已整改重大事故隐患（项）", "YZGZDSGYH"));
        kvList.add(new KVPair("已整改重大事故隐患（项）", "YZGZDSGYH"));
        kvList.add(new KVPair("其中：挂牌督办项（项）", "GPDBX"));
        return this.sumByDateAndAreaAndIndustryTypeAndProperty(startDate, endDate, industryType, province, city, companyId, kvList);
    }

    @Override
    public Map<String, Object> sumByDateAndAreaAndIndustryTypeAndProperty(String startDate, String endDate, String industryType, String province, String city, String companyId, List<KVPair> kvPairList) {
        String sql = "select ";
        for (int i = 0; i < kvPairList.size(); i++) {
            KVPair kvPair = kvPairList.get(i);
            String name = kvPair.getName();
            String value = kvPair.getValue();
            sql += "sum(" + value + ") as '" + name + "'";
            if (i < kvPairList.size() - 1) {
                sql += " , ";
            }
        }
        sql += " FROM t_enforcement as t1 INNER JOIN tjs_special_company as t2 on t1.companyId=t2.id ";
        sql = connectSqlString(startDate, endDate, industryType, province, city, companyId, sql);
        return jdbcTemplate.queryForMap(sql);
    }

    @Override
    public List<KVPair> countByDateAndAreaAndIndustryTypeAndNature(String startDate, String endDate, String industryType, String province, String city, String companyId) {
        return this.countByDateAndAreaAndIndustryTypeAndProperty(startDate, endDate, industryType, province, city, companyId, "ZFJCXZ");
    }

    @Override
    public Map<String, Object> sumByDateAndAreaAndIndustryTypeAndDocument(String startDate, String endDate, String industryType, String province, String city, String companyId) {
        List<KVPair> kvList = new ArrayList<>();
        kvList.add(new KVPair("现场检查记录", "XCJCJL"));
        kvList.add(new KVPair("现场处理措施决定书", "SJSJFK"));
        kvList.add(new KVPair("责令限期整改指令书", "ZLXQZGZLS"));
        kvList.add(new KVPair("强制措施决定书", "QZCZJDS"));
        kvList.add(new KVPair("整改复查意见书", "ZGFCYJS"));
        kvList.add(new KVPair("立案审批表", "LASPB"));
        kvList.add(new KVPair("询问笔录", "XWBL"));
        kvList.add(new KVPair("行政（当场）处罚决定书（单位）（份）", "XZDCCFJDSDW"));
        kvList.add(new KVPair("行政（当场）处罚决定书（个人）（份）", "XZDCCFJDSGR"));
        kvList.add(new KVPair("行政处罚决定书（单位）（份）", "XZCFJDSDW"));
        kvList.add(new KVPair("行政处罚决定书（个人）（份）", "XZCFJDSGR"));
        kvList.add(new KVPair("其他文书", "QTWS"));
        return this.sumByDateAndAreaAndIndustryTypeAndProperty(startDate, endDate, industryType, province, city, companyId, kvList);
    }
}
