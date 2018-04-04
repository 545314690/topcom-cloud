package com.topcom.tjs.utils;

import com.topcom.tjs.domain.TjsSpecialCompany;
import com.topcom.tjs.vo.KVPair;
import com.topcom.tjs.vo.TBHB;
import net.sf.json.JSONObject;
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
                company.setLng(resultSet.getString("lng"));
                company.setLat(resultSet.getString("lat"));
                return company;
            }
        };
    }

    public static RowMapper<JSONObject> tjsAccidentDeathnumberByTime() {
        return new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("年",resultSet.getInt("年"));
                jsonObject.put("月",resultSet.getInt("月"));
                jsonObject.put("死亡人数",resultSet.getInt("死亡人数"));
                jsonObject.put("事故起数",resultSet.getInt("事故起数"));
                return jsonObject;
            }
        };
    }

    public static RowMapper<JSONObject> tjsZfyhByTime() {
        return new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("年",resultSet.getInt("年"));
                jsonObject.put("月",resultSet.getInt("月"));
                jsonObject.put("一般隐患",resultSet.getInt("一般隐患"));
                jsonObject.put("重大隐患",resultSet.getInt("重大隐患"));
                jsonObject.put("违法行为",resultSet.getInt("违法行为"));
                jsonObject.put("执法次数",resultSet.getInt("执法次数"));
                return jsonObject;
            }
        };
    }

}
