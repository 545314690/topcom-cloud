package com.topcom.tjs.service;

import com.topcom.tjs.utils.RowMappers;
import com.topcom.tjs.vo.KVPair;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcServer {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<KVPair> countByAreaAndProperty(String sql) {
        return jdbcTemplate.query(sql, RowMappers.kvPairRowMapper());
    }
}
