package com.topcom.cms.utils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.topcom.cms.common.utils.LogUtil;
import com.topcom.cms.yuqing.config.Constants;
import com.topcom.cms.yuqing.domain.Briefing;
import com.topcom.cms.yuqing.service.BriefingManager;
import com.topcom.cms.yuqing.utils.HttpUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Date;


/**
 * Created by topcom on 2017/5/25 0025.
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class FreemarkerUtilsTest {

    @Autowired
    BriefingManager briefingManager;// = new BriefingManagerImpl();

    @Test
    public void httpTest() throws IOException {
//        List<String> lines = Files.readAllLines(Paths.get("D:\\data\\briefing.json"));
        String object = HttpUtil.doGet("http://192.168.0.12:8081/briefingJson", "");

//        JSONObject jsonObject = JSONObject.fromObject(lines.get(0));
//        Briefing briefing = (Briefing) JSONObject.toBean(jsonObject,Briefing.class);
//        Briefing briefing2 = (Briefing) JSONObject.toBean(JSONObject.fromObject(object), Briefing.class);


//        LogUtil.logger.info(briefing);
        //briefingManager.save(briefing);
//        briefing2.setBriefingBody(null);
//        briefingManager.save(briefing2);
        DBObject dbObject = (BasicDBObject) JSON.parse(object);
        dbObject.put("dateCreated", new Date());
        dbObject.put("dateModified", new Date());
        dbObject.put("type", Briefing.BriefingType.MONTHLY);
        MongoDBUtil.insertDocument(Constants.Mongo.COLLECTION_BRIEFING, dbObject);
//        LogUtil.logger.info(briefing2);
    }

    @Test
    public void testMongoUtil() throws IOException {
        DBObject dbObject = MongoDBUtil.findById(Constants.Mongo.COLLECTION_BRIEFING, "5928035494cdbe0088dce147");
        LogUtil.logger.info(dbObject);
    }


    @Test
    public void savetoMongo() {
        for (int i = 0; i < 5; i++) {
            Briefing briefing = new Briefing();
            briefing.setAuthor("宋丽花" + i);
            briefing.setTitle("测试数据Title" + i);
            briefing.setAttachment("Attachment" + i);
            briefing.setCreateTime("2017年" + i + "月");
            briefing.setOutline(i + "这是一条非常重要的测试数据");
            briefing.setType(Briefing.BriefingType.WEEKLY);
            briefing.setUserId(10020121l + i);
            briefingManager.save(briefing);
        }
        for (int i = 0; i < 5; i++) {
            Briefing briefing = new Briefing();
            briefing.setAuthor("宋丽花" + i);
            briefing.setTitle("测试数据Title" + i);
            briefing.setAttachment("Attachment" + i);
            briefing.setCreateTime("2017年" + i + "月");
            briefing.setOutline(i + "这是一条非常重要的测试数据");
            briefing.setType(Briefing.BriefingType.SPECIAL);
            briefing.setUserId(10020121l + i);
            briefingManager.save(briefing);
        }
    }
}
