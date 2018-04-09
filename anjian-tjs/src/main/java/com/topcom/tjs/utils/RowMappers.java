package com.topcom.tjs.utils;

import com.topcom.tjs.domain.TjsSpecialCompany;
import com.topcom.tjs.vo.CompanyVO;
import com.topcom.tjs.vo.KVPair;
import com.topcom.tjs.vo.TBHB;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lsm on 2018/3/27 0027.
 */
public class RowMappers {
    /**
     * kv 键值对RowMapper
     * @return
     */
    public static RowMapper<KVPair> kvPairRowMapper() {
        return new RowMapper<KVPair>() {
            @Override
            public KVPair mapRow(ResultSet resultSet, int i) throws SQLException {
                KVPair kvPair = new KVPair();
                kvPair.setName(resultSet.getString("name"));
                kvPair.setValue(resultSet.getString("value"));
                return kvPair;
            }
        };
    }

    /**
     * 同比环比RowMapper
     * @return
     */
    public static RowMapper<TBHB> tbhbRowMapper() {
        return new RowMapper<TBHB>() {
            @Override
            public TBHB mapRow(ResultSet resultSet, int i) throws SQLException {
                TBHB tbhb = new TBHB();
                tbhb.setMetricName(resultSet.getString("name"));
                tbhb.setValue(resultSet.getString("value"));
                tbhb.setDate(resultSet.getString("date"));
                tbhb.setUnit(resultSet.getString("unit"));
                return tbhb;
            }
        };
    }

    public static RowMapper<KVPair> kvPairHatRowMapper() {
        return new RowMapper<KVPair>() {
            @Override
            public KVPair mapRow(ResultSet resultSet, int i) throws SQLException {
                KVPair kvPair = new KVPair();
                kvPair.setName(resultSet.getString("keyName"));
                kvPair.setValue(resultSet.getString("value"));
                return kvPair;
            }
        };
    }

    /**
     * 企业RowMapper
     * @return
     */
    public static RowMapper<TjsSpecialCompany> tjsSpecialCompanyRowMapper() {
        return new RowMapper<TjsSpecialCompany>() {
            @Override
            public TjsSpecialCompany mapRow(ResultSet resultSet, int i) throws SQLException {
                TjsSpecialCompany company = new TjsSpecialCompany();
                company.setId(resultSet.getLong("id"));
                company.setCompanyName(resultSet.getString("companyName"));
                company.setLng(resultSet.getDouble("lng"));
                company.setLat(resultSet.getDouble("lat"));
                return company;
            }
        };
    }
    /**
     * 企业RowMapper
     * @return
     */
    public static RowMapper<CompanyVO> companyVORowMapper() {
        return new RowMapper<CompanyVO>() {
            @Override
            public CompanyVO mapRow(ResultSet resultSet, int i) throws SQLException {
                CompanyVO company = new CompanyVO();
                company.setId(resultSet.getLong("id"));
                company.setCompanyName(resultSet.getString("companyName"));
                company.setLng(resultSet.getDouble("lng"));
                company.setLat(resultSet.getDouble("lat"));
                return company;
            }
        };
    }
}
