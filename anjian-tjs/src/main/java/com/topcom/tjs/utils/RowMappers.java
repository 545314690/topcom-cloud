package com.topcom.tjs.utils;

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
}
