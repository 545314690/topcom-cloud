package com.topcom.cms.yuqing.utils;

import com.topcom.cms.common.model.NLPProperty;
import com.topcom.cms.data.domain.ESBaseDomain;
import com.topcom.cms.es.base.BaseService;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.SearchHit;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by lism on 17-7-12.
 */
public class ESEntityHandleThread implements Runnable {

    private Log log = LogFactory.getLog(ESEntityHandleThread.class);
    private SearchHit[] hits;
    private ElasticsearchTemplate elasticsearchTemplate;
    public  ESEntityHandleThread(SearchHit[] hits, ElasticsearchTemplate elasticsearchTemplate) {
        this.hits = hits;
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @Override
    public void run() {
        handleAndSave();
    }


    private void handleAndSave() {
        log.info(Thread.currentThread().getName() + "->开始处理");
        List<JSONObject> list = new ArrayList<>();
        try {

            for (SearchHit searchHit : hits) {
                JSONObject jsonObject = JSONObject.fromObject(searchHit.getSource());
                jsonObject.put("textSimHash", SimHasher.analysis( jsonObject.getString("title") + jsonObject.getString("content")).toString());
                jsonObject.put("titleSimHash",SimHasher.analysis( jsonObject.getString("title")).toString());
//                T esBaseDomain = (T) JSONObject.toBean(jsonObject,clazz);
//                esBaseDomain.setTextSimHash(SimHasher.analysis(esBaseDomain.getTitle()+""+esBaseDomain.getContent()).toString());
//                esBaseDomain.setTitleSimHash(SimHasher.analysis(esBaseDomain.getTitle()).toString());
                list.add(jsonObject);
                //handleOne(esBaseDomain);
            }
            this.saveJson("yuqing","article",list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(Thread.currentThread().getName() + "->处理完成");
    }

    private void saveJson(String yuqing, String article, List<JSONObject> jsonObjects) {
        Client client = elasticsearchTemplate.getClient();
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
        for (int i=0;i<jsonObjects.size();i++){
            JSONObject jsonObject = jsonObjects.get(i);
            IndexRequestBuilder indexRequestBuilder = client.prepareIndex(yuqing,article).setSource(jsonObject.toString())
                    .setId(jsonObject.get("id").toString());
            bulkRequestBuilder.add(indexRequestBuilder);

        }
        bulkRequestBuilder.execute().actionGet();
    }
}
