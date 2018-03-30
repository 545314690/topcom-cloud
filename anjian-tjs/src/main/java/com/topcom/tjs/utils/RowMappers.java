package com.topcom.tjs.utils;

import com.topcom.tjs.domain.TjsSpecialCompany;
import com.topcom.tjs.vo.KVPair;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lsm on 2018/3/27 0027.
 */
public class RowMappers {
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
    public static RowMapper<TjsSpecialCompany> tjsSpecialCompanyRowMapper() {
        return new RowMapper<TjsSpecialCompany>() {
            @Override
            public TjsSpecialCompany mapRow(ResultSet resultSet, int i) throws SQLException {
                TjsSpecialCompany company = new TjsSpecialCompany();
                company.setId(resultSet.getLong("id"));
                company.setCompanyName(resultSet.getString("companyName"));
                company.setLng(resultSet.getString("lng"));
                company.setLat(resultSet.getString("lat"));
                return company;
            }
        };
    }
}
