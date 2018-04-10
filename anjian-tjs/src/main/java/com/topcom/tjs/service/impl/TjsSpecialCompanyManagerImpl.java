package com.topcom.tjs.service.impl;

import com.spatial4j.core.shape.Rectangle;
import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.tjs.dao.TjsSpecialCompanyDao;
import com.topcom.tjs.domain.TjsSpecialCompany;
import com.topcom.tjs.service.TjsSpecialCompanyManager;
import com.topcom.tjs.utils.GeoUtil;
import com.topcom.tjs.utils.RowMappers;
import com.topcom.tjs.vo.KVPair;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
@Service("tjsSpecialCompanyManager")
@Transactional
public class TjsSpecialCompanyManagerImpl extends GenericManagerImpl<TjsSpecialCompany, Long> implements TjsSpecialCompanyManager {

    TjsSpecialCompanyDao tjsSpecialCompanyDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setTjsSpecialCompanyDao(TjsSpecialCompanyDao tjsSpecialCompanyDao) {
        this.tjsSpecialCompanyDao = tjsSpecialCompanyDao;
        this.dao = tjsSpecialCompanyDao;
    }

    @Override
    public List<TjsSpecialCompany> findByArea(String industryType, String province, String city) {
        String sql = "SELECT * FROM tjs_special_company ";
//        String sql = "SELECT id,companyName,lat,lng FROM tjs_special_company ";
        boolean flag = false;
        if (StringUtils.isNotBlank(industryType)) {
            flag = true;
            sql += " WHERE industryType='" + industryType + "'";
        }
        if (StringUtils.isNotBlank(province)) {
            if (flag) {
                sql += " AND ";
            } else {
                sql += " WHERE ";
                flag = true;
            }
            sql += " province='" + province + "'";
        }
        if (StringUtils.isNotBlank(city)) {
            if (flag) {
                sql += " AND ";
            } else {
                sql += " WHERE ";
            }
            sql += " city='" + city + "'";
        }
        return jdbcTemplate.query(sql, RowMappers.tjsSpecialCompanyRowMapper());
    }

    @Override
    public List<KVPair> countByIndustryTypeAndAreaAndProperty(String industryType, String province, String city, String property) {
        String sql = "select " + property + " as name, count(1) as value FROM tjs_special_company ";
        boolean flag = false;
        if (StringUtils.isNotBlank(industryType)) {
            sql += " WHERE industryType= '" + industryType + "'";
            flag = true;
        }
        if (StringUtils.isNotBlank(province)) {
            if (flag) {
                sql += " AND ";
            } else {
                sql += " WHERE ";
                flag = true;
            }
            sql += " province='" + province + "'";
        }
        if (StringUtils.isNotBlank(city)) {
            if (flag) {
                sql += " AND ";
            } else {
                sql += " WHERE ";
            }
            sql += " city='" + city + "'";
        }
        sql += " group by name ORDER BY VALUE DESC ";
        return jdbcTemplate.query(sql, RowMappers.kvPairRowMapper());
    }

    @Override
    public List<KVPair> countByAreaAndIndustryTypeAndScale(String industryType, String province, String city) {
        return this.countByIndustryTypeAndAreaAndProperty(industryType, province, city, "companyType");
    }

    @Override
    public List<KVPair> countByAreaAndIndustryTypeAndAdministrative(String industryType, String province, String city) {
        return this.countByIndustryTypeAndAreaAndProperty(industryType, province, city, "companyAttribute");
    }

    @Override
    public List<KVPair> countByAreaAndIndustryTypeAndProductType(String industryType, String province, String city) {
        return this.countByIndustryTypeAndAreaAndProperty(industryType, province, city, "productType");
    }

    @Override
    public List<KVPair> countByAreaAndIndustryTypeAndLicence(String industryType, String province, String city) {
        return this.countByIndustryTypeAndAreaAndProperty(industryType, province, city, "licence");
    }

    @Override
    public List<KVPair> countByAreaAndIndustryTypeAndcountByLicenceDate(String industryType, String province, String city) {
        /**先根据天数差判断是否到期，再根据年数差+1判断到期年份
         * select
         case when (TIMESTAMPDIFF(DAY,NOW(),licenceEndDate))<0 then '已到期'
         else CONCAT(TIMESTAMPDIFF(YEAR,NOW(),licenceEndDate)+1,'年到期')
         end as name, count(1) as value FROM tjs_special_company GROUP BY name;
         */
        String condition = " case when (TIMESTAMPDIFF(DAY,NOW(),licenceEndDate))<0 then '已到期' \n" +
                "else CONCAT(TIMESTAMPDIFF(YEAR,NOW(),licenceEndDate)+1,'年到期')   \n" +
                "end ";
        return this.countByIndustryTypeAndAreaAndProperty(industryType, province, city, condition);
    }

    @Override
    public List<TjsSpecialCompany> findByCircleArea(Double lat, Double lng, Double radius) {
        //先根据原点和半径求出矩形区域
        Rectangle rect = GeoUtil.geoRectangle(lat, lng, radius);
        //查询出在矩形区域内的企业
        String sql = "SELECT * FROM tjs_special_company  WHERE (lng BETWEEN ? AND ?) AND (lat BETWEEN ? AND ?); ";
        List<TjsSpecialCompany> specialCompanyList = jdbcTemplate.query(sql, new Object[]{rect.getMinX(), rect.getMaxX(), rect.getMinY(), rect.getMaxY()}, RowMappers.tjsSpecialCompanyRowMapper());
        //根据原点到企业所在位置的距离和半径对比过滤出在圆形范围内的点，去掉矩形内圆形外的点
        List<TjsSpecialCompany> companies = specialCompanyList.stream().filter(tjsSpecialCompany -> {
            return GeoUtil.filter(lat, lng, tjsSpecialCompany.getLat(), tjsSpecialCompany.getLng(), radius);
        }).collect(Collectors.toList());
        return companies;
    }

    @Override
    public List<KVPair> countByAreaAndIndustryType(String industryType, String province, String city) {
        if (StringUtils.isBlank(province) && (StringUtils.isBlank(city))) {
            return this.countByIndustryTypeAndAreaAndProperty(industryType, province, null, "province");
        } else if (StringUtils.isBlank(city)) {
            return this.countByIndustryTypeAndAreaAndProperty(industryType, province, null, "city");
        } else if (StringUtils.isNotBlank(province) && (StringUtils.isNotBlank(city))) {
            return this.countByIndustryTypeAndAreaAndProperty(industryType, province, city, "county");
        }
        return new ArrayList<KVPair>();
    }
}
