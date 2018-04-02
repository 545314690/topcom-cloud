package com.topcom.tjs.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.tjs.dao.TjsAccidentDao;
import com.topcom.tjs.domain.TjsAccident;
import com.topcom.tjs.service.TjsAccidentManager;
import com.topcom.tjs.utils.RowMappers;
import com.topcom.tjs.vo.KVPair;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
@Service("tjsAccidentManager")
@Transactional
public class TjsAccidentManagerImpl extends GenericManagerImpl<TjsAccident, Long> implements TjsAccidentManager {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    TjsAccidentDao tjsAccidentDao;

    @Autowired
    public void setTjsAccidentDao(TjsAccidentDao tjsAccidentDao) {
        this.tjsAccidentDao = tjsAccidentDao;
        this.dao = tjsAccidentDao;
    }


    public  List<KVPair> countByGroup(String startDate, String endDate,String province, String city,String property,String industryType){
        String sql = "select " + property + " as keyName, count(1) as value FROM tjs_special_company ";
        sql = connetSqlString(startDate, endDate, city, industryType, sql);
        sql += " group by keyName ORDER BY VALUE DESC ";
        return jdbcTemplate.query(sql, RowMappers.kvPairHatRowMapper());
    }
    @Override
    public List<KVPair> countByAreaAndProperty(String startDate, String endDate,String province, String city,String property,String industryType) {
        String sql = "select " + property + " as keyName, count(1) as value FROM tjs_accident  as acc INNER JOIN tjs_death_person as person on acc.id=person.accidentId";
        sql = connetSqlString(startDate, endDate, city, industryType, sql);
        sql += " group by keyName ORDER BY VALUE DESC ";
        return jdbcTemplate.query(sql, RowMappers.kvPairHatRowMapper());
    }
    @Override
    public List<KVPair> countByAreaAndGender(String startDate, String endDate,String province, String city,String industryType) {
        return this.countByAreaAndProperty(startDate,endDate,province,city,"gender",industryType);
    }

    @Override
    public List<KVPair> countByAreaAndType(String startDate, String endDate,String province, String city,String industryType) {
        return this.countByAreaAndProperty(startDate,endDate,province,city,"companyScale",industryType);
    }

    @Override
    public List<KVPair> countByAreaAndManageType(String startDate, String endDate,String province, String city,String industryType) {
        return this.countByAreaAndProperty(startDate,endDate,province,city,"manageType",industryType);
    }

    @Override
    public List<KVPair> countByStatusAndInsurance(String startDate, String endDate,String province, String city,String industryType) {
        String condition = "case when industrialInsurance = 1 and status = '死亡' then '死亡有工伤保险' \n" +
                "when industrialInsurance = 1 and status = '重伤' then '重伤无工伤保险' \n" +
                "when industrialInsurance = 0 and status = '死亡' then '死亡无工伤保险' \n" +
                "when industrialInsurance = 0 and status = '重伤' then '重伤有工伤保险' else \"未知\" end";
        return this.countByAreaAndProperty(startDate,endDate,province,city,condition,industryType);
    }

    @Override
    public List<KVPair> countByProfessionDeath(String startDate, String endDate,String province, String city,String industryType) {
        String condition = "case when professionDeath = 1 then '职业伤亡'" +
                "else '非职业伤亡' end";
        return this.countByAreaAndProperty(startDate,endDate,province,city,condition,industryType);
    }

    @Override
    public List<KVPair> countByEducation(String startDate, String endDate,String province, String city,String industryType) {
        return this.countByAreaAndProperty(startDate,endDate,province,city,"education",industryType);
    }

    @Override
    public List<KVPair> countByAttribute(String startDate, String endDate,String province, String city,String industryType) {
        String sql = "select companyAttribute as keyName, count(1) as value FROM tjs_accident";
        sql = connetSqlString(startDate, endDate, city, industryType, sql);
        sql += " group by keyName ORDER BY VALUE DESC ";
        return jdbcTemplate.query(sql, RowMappers.kvPairHatRowMapper());
    }

    private String connetSqlString(String startDate, String endDate, String city, String industryType, String sql) {
        boolean flag = false;
        if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
            sql += " WHERE happenedTime BETWEEN '" + startDate + "' AND '" + endDate + "' ";
            flag = true;
        }
        if (StringUtils.isNotBlank(industryType)) {
            if (flag) {
                sql += " AND ";
            } else {
                sql += " WHERE ";
                flag = true;
            }
            sql += " industryType= '" + industryType + "'";
        }
        if (StringUtils.isNotBlank(city)) {
            if (flag) {
                sql += " AND ";
            } else {
                sql += " WHERE ";
            }
            sql += " city='" + city + "'";
        }
        return sql;
    }

    @Override
    public List<KVPair> countByCFLB(String startDate, String endDate,String type, String province, String city, String industryType) {
        if (StringUtils.isBlank(type)){
            type = "厅局级";
        }
        Map map = countByInperson(startDate,endDate,province,city,industryType);
        List<KVPair> result =new ArrayList<>();
        for (Object key:map.keySet()){
            if (key.toString().contains(type.replaceAll("级",""))){
                result.add(new KVPair(key.toString(),String.valueOf(map.get(key))));
            }
        }
        return result;
    }

    @Override
    public List<KVPair> countByCFRY(String startDate, String endDate,String province, String city, String industryType) {
        List<KVPair> result = new ArrayList<>();
        Map<String,Long> resultMap = new HashMap<>();
        Map map = countByInperson(startDate,endDate,province,city,industryType);
        for (Object key:map.keySet()){
            String JB="";
            if (key.toString().contains("省")){
                JB = "省部级";
            }else if (key.toString().contains("厅")){
                JB = "厅局级";
            }else if (key.toString().contains("县")){
                JB = "县处级";
            }else if (key.toString().contains("乡")){
                JB = "乡科级";
            }else {
                JB = "其他";
            }
            if (resultMap.containsKey(JB)){
                resultMap.put(JB,resultMap.get(JB)+Long.valueOf(map.get(key).toString()));
            }else {
                resultMap.put(JB,Long.valueOf(map.get(key).toString()));
            }

        }
        for (String key:resultMap.keySet()){
            result.add(new KVPair(key,String.valueOf(resultMap.get(key))));
        }
        return result;
    }

    private Map countByInperson(String startDate, String endDate,String province, String city, String industryType){
        String condition = "sum(ZJDJCFA) as 政纪党纪处分人省部级,\n" +
                "sum(ZJDJCFB) as 政纪党纪处分人厅局级,\n" +
                "sum(ZJDJCFC) as 政纪党纪处分人县处级,\n" +
                "sum(ZJDJCFD) as 政纪党纪处分人乡科级,\n" +
                "sum(ZJDJCFE) as 政纪党纪处分人其他,\n" +
                "sum(ZJDJCFJGA) as 政纪党纪处分机关省部级,\n" +
                "sum(ZJDJCFJGB) as 政纪党纪处分机关厅局级,\n" +
                "sum(ZJDJCFJGC) as 政纪党纪处分机关县处级,\n" +
                "sum(ZJDJCFJGD) as 政纪党纪处分机关乡科级,\n" +
                "sum(ZJDJCFJGE) as 政纪党纪处分机关其他,\n" +
                "sum(ZJDJCFQYA) as 政纪党纪处分企业省部级,\n" +
                "sum(ZJDJCFQYB) as 政纪党纪处分企业厅局级,\n" +
                "sum(ZJDJCFQYC) as 政纪党纪处分企业县处级,\n" +
                "sum(ZJDJCFQYD) as 政纪党纪处分企业乡科级,\n" +
                "sum(ZJDJCFQYE) as 政纪党纪处分企业其他,\n" +
                "sum(DJCFA) as 党纪处分人省部级,\n" +
                "sum(DJCFB) as 党纪处分人厅局级,\n" +
                "sum(DJCFC) as 党纪处分人县处级,\n" +
                "sum(DJCFD) as 党纪处分人乡科级,\n" +
                "sum(DJCFE) as 党纪处分人其他,\n" +
                "sum(DJCFJGA) as 党纪处分机关省部级,\n" +
                "sum(DJCFJGB) as 党纪处分机关厅局级,\n" +
                "sum(DJCFJGC) as 党纪处分机关县处级,\n" +
                "sum(DJCFJGD) as 党纪处分机关乡科级,\n" +
                "sum(DJCFJGE) as 党纪处分机关其他,\n" +
                "sum(DJCFQYA) as 党纪处分企业省部级,\n" +
                "sum(DJCFQYB) as 党纪处分企业厅局级,\n" +
                "sum(DJCFQYC) as 党纪处分企业县处级,\n" +
                "sum(DJCFQYD) as 党纪处分企业乡科级,\n" +
                "sum(DJCFQYE) as 党纪处分企业其他,\n" +
                "sum(ZJXSZRA) as 追究刑事责任省部级,\n" +
                "sum(ZJXSZRB) as 追究刑事责任厅局级,\n" +
                "sum(ZJXSZRC) as 追究刑事责任县处级,\n" +
                "sum(ZJXSZRD) as 追究刑事责任乡科级,\n" +
                "sum(ZJXSZRE) as 追究刑事责任其他,\n" +
                "sum(ZJXSZRJGA) as 追究刑事责任机关省部级,\n" +
                "sum(ZJXSZRJGB) as 追究刑事责任机关厅局级,\n" +
                "sum(ZJXSZRJGC) as 追究刑事责任机关县处级,\n" +
                "sum(ZJXSZRJGD) as 追究刑事责任机关乡科级,\n" +
                "sum(ZJXSZRJGE) as 追究刑事责任机关其他,\n" +
                "sum(ZJXSZRQYA) as 追究刑事责任企业省部级,\n" +
                "sum(ZJXSZRQYB) as 追究刑事责任企业厅局级,\n" +
                "sum(ZJXSZRQYC) as 追究刑事责任企业县处级,\n" +
                "sum(ZJXSZRQYD) as 追究刑事责任企业乡科级,\n" +
                "sum(ZJXSZRQYE) as 追究刑事责任企业其他 ";


        String sql = "select "+condition+" from t_accident_investigation as t1 INNER JOIN tjs_accident t2 on t1.accidentId = t2.ID ";
        sql = connetSqlString(startDate, endDate, city, industryType, sql);
        return jdbcTemplate.queryForMap(sql);
    }
    @Override
    public Page<TjsAccident> findByCompanyIdAndHappenedTimeBetween(Long accidentId, Date startDate, Date endDate, Pageable pageable) {
        return tjsAccidentDao.findByCompanyIdAndHappenedTimeBetween(accidentId, startDate, endDate,pageable);
    }

    @Override
    public List<KVPair> countByArea(String startDate, String endDate,String province, String city, String industryType) {
        String condition ="";
        if (StringUtils.isBlank(province)){
            condition="province";
        }else if (StringUtils.isBlank(city)){
            condition="city";
        }else {
            condition = "county";
        }
        return this.countByAreaAndProperty(startDate,endDate,province,city,condition,industryType);
    }
}
