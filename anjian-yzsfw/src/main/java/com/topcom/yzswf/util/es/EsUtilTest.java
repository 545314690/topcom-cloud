package com.topcom.yzswf.util.es;


import com.topcom.yzswf.service.ElasticSearchService;
import com.topcom.yzswf.util.Page;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by lsm on 2018/3/12 0012.
 */
public class EsUtilTest {

    public static void test() throws IOException {


    }
    public static void main(String[] args) {
        try {

            String idxName = "test";
            String idxType = "attachment";
            XContentBuilder map = jsonBuilder().startObject()
                    .startObject(idxType)
                    .startObject("properties")
                    .startObject("file")
                    .field("type", "attachment")
                    .startObject("fields")
                    .startObject("title")
                    .field("store", "yes")
                    .endObject()
                    .startObject("file")
                    .field("term_vector","with_positions_offsets")
                    .field("store","yes")
                    .endObject()
                    .endObject()
                    .endObject()
                    .endObject().endObject()
                    .endObject();
            ElasticSearchService service = new ElasticSearchService("tc-es5", "192.168.1.12", 19300);
//            CreateIndexResponse resp = service.client.admin().indices().prepareCreate(idxName)
//                    .setSettings(Settings.builder()
//                            .put("index.number_of_shards", 1)
//                            .put("index.number_of_replicas",1)
//                    ).addMapping(idxType,map)
//                    .get();
//            EsIndexFile esIndexFile = new EsIndexFile("D:\\Shiro教程.pdf");
//            IndexResponse resp =  service.insertDataWithPipeline("test","document","attachment", JSONObject.fromObject(esIndexFile).toString());
//            System.out.println(resp.toString());
            ESQueryBuilderConstructor constructor = new ESQueryBuilderConstructor();
            constructor.must(new ESQueryBuilders().queryString("attachment.content:在线会话管理"));
            constructor.setSize(1);  //查询返回条数，最大 10000
            constructor.setFrom(0);  //分页查询条目起始位置， 默认0
//            constructor.setDesc("pubTime"); //排序
//
           Page list = service.search("test", "document", constructor);
////            Map<Object, Object> map = service.statSearch("bank", "account", constructor, "state");
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
