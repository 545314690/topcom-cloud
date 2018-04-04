package com.topcom.tjs.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.tjs.dao.TjsEnforcementDao;
import com.topcom.tjs.domain.TjsEnforcement;
import com.topcom.tjs.service.TjsEnforcementManager;
import com.topcom.tjs.utils.RowMappers;
import com.topcom.tjs.vo.KVPair;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

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

    @Override
    public Map<String,List<KVPair>> countByEnforcet(String startDate, String endDate, String industryType, String province, String city) {
        String[] keyArray = new String[]{"死亡人数","事故起数","执法次数","违法行为"};
        Map<String,List<KVPair>> result = new HashMap<>();
        //执法行为  违法行为
        //死亡人数  事故起数
        String sqlAcc = "select DATE_FORMAT(happenedTime, '%Y') as 年,DATE_FORMAT(happenedTime, '%m') as 月,sum(deathNumber) as 死亡人数,count(1) as 事故起数 " +
                "from tjs_accident as t1 INNER JOIN tjs_special_company as t2 " +
                "ON " +
                "t1.companyId = t2.ID ";
        boolean flag = false;
        if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
            sqlAcc += " WHERE happenedTime BETWEEN '" + startDate + "' AND '" + endDate + "' ";
            flag = true;
        }
        if (StringUtils.isNotBlank(industryType)) {
            if (flag) {
                sqlAcc += " AND ";
            } else {
                sqlAcc += " WHERE ";
                flag = true;
            }
            sqlAcc += " t2.industryType= '" + industryType + "'";
        }
        if (StringUtils.isNotBlank(province)) {
            if (flag) {
                sqlAcc += " AND ";
            } else {
                sqlAcc += " WHERE ";
                flag = true;
            }
            sqlAcc += " t2.province='" + province + "'";
        }
        if (StringUtils.isNotBlank(city)) {
            if (flag) {
                sqlAcc += " AND ";
            } else {
                sqlAcc += " WHERE ";
            }
            sqlAcc += " t2.city='" + city + "'";
        }

        sqlAcc =sqlAcc+  "group by 年,月";
       // sqlAcc = connectSqlString(startDate, endDate, industryType, province, city, "", sqlAcc);
        List<JSONObject> acc = jdbcTemplate.query(sqlAcc,RowMappers.tjsAccidentDeathnumberByTime());

        String sqlEnforcet = "select DATE_FORMAT(ZFJCJZSJ, '%Y') as 年," +
                "DATE_FORMAT(ZFJCJZSJ, '%m') as 月," +
                "sum(CCAQSCWFWGXWX) as 违法行为," +
                "sum(CCYBSGYHX) as 一般隐患," +
                "sum(CCZDSGYHX) as 重大隐患," +
                "sum(ZFWF) as 执法次数 " +
                "from t_enforcement as t1 INNER JOIN tjs_special_company as t2 " +
                "ON " +
                "t1.companyId = t2.ID ";
        sqlEnforcet = connectSqlString(startDate, endDate, industryType, province, city, "", sqlEnforcet);
        sqlEnforcet = sqlEnforcet +"group by 年,月 ";
        List<JSONObject> enforcet = jdbcTemplate.query(sqlEnforcet,RowMappers.tjsZfyhByTime());
        List<KVPair> deathNumberKV= new ArrayList<>();
        List<KVPair> accNumber = new ArrayList<>();
        List<KVPair> zhifa = new ArrayList<>();
        List<KVPair> weifa = new ArrayList<>();
        List<KVPair> ybyh = new ArrayList<>();
        List<KVPair> zdyh = new ArrayList<>();
        for (int i=0;i<acc.size();i++){
            JSONObject jsonObject = acc.get(i);
            String key = jsonObject.get("年")+"-"+jsonObject.get("月");
            deathNumberKV.add(new KVPair(key,jsonObject.get("死亡人数").toString()));
            accNumber.add(new KVPair(key,jsonObject.get("事故起数").toString()));
        }
        for (int i=0;i<enforcet.size();i++){
            JSONObject jsonObject =  enforcet.get(i);
            String key = jsonObject.get("年")+"-"+jsonObject.get("月");
            zhifa.add(new KVPair(key,jsonObject.get("违法行为").toString()));
            weifa.add(new KVPair(key,jsonObject.get("执法次数").toString()));
            ybyh.add(new KVPair(key,jsonObject.get("一般隐患").toString()));
            zdyh.add(new KVPair(key,jsonObject.get("重大隐患").toString()));
        }
        result.put("死亡人数",deathNumberKV);
        result.put("事故起数",accNumber);
        result.put("违法行为",zhifa);
        result.put("执法次数",weifa);
        result.put("一般隐患",ybyh);
        result.put("重大隐患",zdyh);
        return result;
    }
    @Override
    public Map<String, Object> countByJGZG(String startDate, String endDate, String industryType, String province, String city) {
        String sql = "select sum(CCYBSGYHX) as 一般隐患 ," +
                "sum(CCZDSGYHX) as 重大隐患, " +
                "(sum(YZGYBSGYH)+ sum(YZGZDSGYH))*100/(sum(CCYBSGYHX)+sum(CCZDSGYHX)) as 整改率 " +
                "from t_enforcement t1 INNER JOIN tjs_special_company t2 on t1.companyId=t2.ID ";
        sql = connectSqlString(startDate, endDate, industryType, province, city, "", sql);
        String sql_acc = sql + " and t2.id IN (SELECT companyId from tjs_accident acc GROUP BY companyId) ";
        String sql_not = sql + "and t2.id NOT IN (SELECT companyId from tjs_accident acc GROUP BY companyId)";
        Map accMap = jdbcTemplate.queryForMap(sql_acc);
        Map notMap = jdbcTemplate.queryForMap(sql_not);
        Map resultMap = new HashMap();
        resultMap.put("事故企业",accMap);
        resultMap.put("非事故企业",notMap);
        return resultMap;
    }

    @Override
    public Map countByZFPZ(String startDate, String endDate, String industryType, String province, String city) {
        String sql = "select JFJCLB as name,count(1) as  value  from t_enforcement t1 INNER JOIN tjs_special_company t2 on t1.companyId=t2.ID " ;
        sql = connectSqlString(startDate, endDate, industryType, province, city, "", sql);
        String sql_acc = sql + " and t2.id IN (SELECT companyId from tjs_accident acc GROUP BY companyId)  group by JFJCLB";
        String sql_not = sql + "and t2.id NOT IN (SELECT companyId from tjs_accident acc GROUP BY companyId)  group by JFJCLB";
        List<KVPair> accList = jdbcTemplate.query(sql_acc, RowMappers.kvPairRowMapper());
        List<KVPair> notList = jdbcTemplate.query(sql_acc, RowMappers.kvPairRowMapper());
        Map resultMap = new HashMap();
        resultMap.put("事故企业",accList);
        resultMap.put("非事故企业",notList);
        return resultMap;
    }

    @Override
    public List<KVPair> countByArea(String startDate, String endDate, String province, String city, String industryType) {
        String condition ="";
        if (StringUtils.isBlank(province)){
            condition="province";
        }else if (StringUtils.isBlank(city)){
            condition="city";
        }else {
            condition = "county";
        }
        String sql = "select "+condition+" as name ,count(1) as value FROM " +
                "tjs_accident AS t1 " +
                "INNER JOIN t_enforcement AS t2 ON t1.companyId = t2.ID ";

        sql = connectSqlString(startDate, endDate, industryType, province, city, "", sql);
        sql = sql+" group by name";
        return jdbcTemplate.query(sql, RowMappers.kvPairRowMapper());
    }
}
