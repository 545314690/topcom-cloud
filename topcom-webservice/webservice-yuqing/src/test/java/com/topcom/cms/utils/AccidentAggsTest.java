package com.topcom.cms.utils;

import com.mongodb.DBObject;
import com.topcom.cms.dao.MongoDao;
import com.topcom.cms.dao.impl.MongoDaoImpl;
import com.topcom.cms.mongo.Aggregate;
import com.topcom.cms.mongo.KVO;
import com.topcom.cms.mongo.MongoParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lism on 17-6-21.
 */
public class AccidentAggsTest {
    Log logger = LogFactory.getLog(AccidentAggsTest.class);
    MongoDao mongoDao = new MongoDaoImpl();

    @Test
    public void testAggregateByDate() throws Exception {
        List<DBObject> result = new ArrayList<>();
        MongoParam param = new MongoParam();
        param.setFrom("acc");
        List select = new ArrayList<KVO>();
        select.add(new KVO("deathnumber", "sum", "deathnumber", "int"));
        param.setSelect(select);
        List where = new ArrayList<KVO>();
        where.add(new KVO("atype", "煤矿"));
        param.setWhere(where);
        List groupBy = new ArrayList<KVO>();
//        groupBy.add(new KVO("adate", "year", "year","date"));
        groupBy.add(new KVO("adate", "adate", "dayOfWeek","date"));
        param.setGroupBy(groupBy);
        List<KVO> sortBy = new ArrayList<>();
        sortBy.add(new KVO("_id.year","-1"));
        sortBy.add(new KVO("_id.adate","-1"));
        param.setSortBy(sortBy);
        Object obj = mongoDao.aggregate(new Aggregate(param));
        System.out.println(obj);
    }
    @Test
    public void testSelectByDate() throws Exception {
        List<DBObject> result = new ArrayList<>();
        MongoParam param = new MongoParam();
        param.setFrom("acc");
        List select = new ArrayList<KVO>();
        select.add(new KVO("deathnumber", "sum", "deathnumber", "int"));
        param.setSelect(select);
        List where = new ArrayList<KVO>();
        where.add(new KVO("atype", "煤矿"));
        where.add(new KVO("atime", "1483200000",">="));
        where.add(new KVO("atime", "1485792000","<="));
        param.setWhere(where);
        List groupBy = new ArrayList<KVO>();
        groupBy.add(new KVO("adate", "adate", "month","date"));
        param.setGroupBy(groupBy);
        Object obj = mongoDao.aggregate(new Aggregate(param));
        System.out.println(obj);
        System.out.println(new Date(1483200000*1000));
        System.out.println(new Date(1485792000));
    }
}
