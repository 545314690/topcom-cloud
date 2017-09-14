package com.topcom.cms.yuqing.controller;

import com.topcom.cms.data.domain.ESConf;
import com.topcom.cms.data.domain.News;
import com.topcom.cms.es.service.CommentsService;
import com.topcom.cms.es.service.NewsService;
import com.topcom.cms.es.utils.QueryBuilderUtils;
import com.topcom.cms.nlp.service.impl.NLPManagerImpl;
import com.topcom.cms.yuqing.utils.*;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.HasParentQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by topcom on 2017/7/6 0006.
 */
@RestController
@RequestMapping("/etl/")
public class EtlController {

    private int THREAD_NUM = 20;
    protected ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUM);
    protected final Log logger = LogFactory.getLog(this.getClass());


    @Autowired
    protected ElasticsearchTemplate elasticsearchTemplate;


    @Autowired
    public  NewsService newsService;

    /**
     * v1:为新闻添加simhash
     * @param limit
     * @return
     */
    @ApiOperation("newsSimHashEtl")
    @RequestMapping(value = "/newsSimHashEtl", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object newsSimHashEtl( @RequestParam Integer limit) throws InterruptedException {
        long count =0;
        SearchResponse searchResponse = scanNews("",limit);
        while (searchResponse.getHits().getHits().length>0){
            ESEntityHandleThread esEntityHandleThread = new ESEntityHandleThread(searchResponse.getHits().getHits(),elasticsearchTemplate);
            int activeThread=((ThreadPoolExecutor)executorService).getActiveCount();
            while(activeThread==THREAD_NUM){
                Thread.sleep(100);
                activeThread=((ThreadPoolExecutor)executorService).getActiveCount();
            }
            executorService.submit(esEntityHandleThread);
            System.out.println(((ThreadPoolExecutor)executorService).getActiveCount());
            //((ThreadPoolExecutor)executorService).getActiveCount();
            //saveJson("yuqing","article",etlSimHash(searchResponse.getHits().hits()));
            count=count+searchResponse.getHits().getHits().length;
            logger.error("处理新闻条数："+count+" \n 完成度"+(double)count*100/searchResponse.getHits().getTotalHits()+"%");
            searchResponse = scanNews(searchResponse.getScrollId(),limit);
        }
        return "done";
    }

    private void saveJson(String yuqing, String article, List<JSONObject> jsonObjects) {
        Client client = elasticsearchTemplate.getClient();
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
        for (int i=0;i<jsonObjects.size();i++){
            JSONObject jsonObject = jsonObjects.get(i);
            IndexRequestBuilder indexRequestBuilder = client.prepareIndex("yuqing","article").setSource(jsonObject.toString())
                    .setId(jsonObject.get("id").toString());
            bulkRequestBuilder.add(indexRequestBuilder);

        }
        bulkRequestBuilder.execute().actionGet();
    }

    private void updateJson(String yuqing, String article, List<JSONObject> jsonObjects) {
        Client client = elasticsearchTemplate.getClient();
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
        BulkRequestBuilder bulkdel = client.prepareBulk();
        for (int i=0;i<jsonObjects.size();i++){
            JSONObject jsonObject = jsonObjects.get(i);
            bulkdel.add(client.prepareDelete("yuqing","article",jsonObject.get("weiboId").toString()).request());
            IndexRequestBuilder indexRequestBuilder = client.prepareIndex("yuqing","article").setSource(jsonObject.toString())
                    .setId(jsonObject.get("id").toString());
            bulkRequestBuilder.add(indexRequestBuilder);

        }
        bulkdel.execute().actionGet();
        bulkRequestBuilder.execute().actionGet();
    }

    public  List<JSONObject> etlSimHash(SearchHit[] hits) {

        List<JSONObject> commentsList = new ArrayList<>();
        for (int i=0;i<hits.length;i++){
            JSONObject jsonObject = JSONObject.fromObject(hits[i].sourceAsString());
            jsonObject.put("textSimHash", SimHasher.analysis( jsonObject.getString("title") + jsonObject.getString("content")).toString());
            jsonObject.put("titleSimHash",SimHasher.analysis( jsonObject.getString("title")).toString());
            commentsList.add(jsonObject);
        }
        return commentsList;
    }

    /**
     * 微博id->weiboId
     * md5（id）->微博id
     * @param limit
     * @return
     */
    @ApiOperation("bbsEtl")
    @RequestMapping(value = "/bbsEtl", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object bbsEtl( @RequestParam Integer limit) {
        long count =0;
        long count1 =0;
        SearchResponse searchResponse = scanBbs("",limit);
        while (searchResponse.getHits().getHits().length>0){
            SearchHit[] hits = searchResponse.getHits().hits();
            List<JSONObject> commentsList = new ArrayList<>();
            for (int i=0;i<hits.length;i++){
                JSONObject jsonObject = JSONObject.fromObject(hits[i].sourceAsMap());
                String type = jsonObject.get("type").toString();
                if (type.equals("Bbs")){
                    Object object = jsonObject.get("floorNum");
                    if (object!=null&&Integer.valueOf(object.toString())>0){
                        commentsList.add(jsonObject);
                    }
                }
            }
          //  String path = "D:\\data\\bbs\\"+"bbs"+(count1/50000)+".txt";
            String path = "/home/yuqing/data/esFile/"+"bbs"+(count1/50000)+".txt";
            save2File(commentsList,path);
            dleteEsDoc("yuqing","article",commentsList);
            count1=count1+commentsList.size();
            count=count+searchResponse.getHits().getHits().length;
            logger.error("删除BBS条数："+count1);
            logger.error("处理BBS条数："+count+"\n 完成度"+(double)count*100/searchResponse.getHits().getTotalHits()+"%");
            searchResponse = scanBbs(searchResponse.getScrollId(),limit);
        }
        return "done";
    }

    private void dleteEsDoc(String yuqing, String article, List<JSONObject> commentsList) {
        Client client = elasticsearchTemplate.getClient();
        BulkRequestBuilder bulkdel = client.prepareBulk();
        for (int i=0;i<commentsList.size();i++){
            JSONObject jsonObject = commentsList.get(i);
            bulkdel.add(client.prepareDelete("yuqing","article",jsonObject.get("id").toString()).request());
        }
        bulkdel.execute().actionGet();
    }

    private void save2File(List<JSONObject> commentsList,String path) {
        StringBuffer stringB = new StringBuffer();
        for (JSONObject json:commentsList){
            stringB.append(json.toString());
            stringB.append("\n");
        }
        try {
            FileUtil.write(stringB.toString(),path,true);
        } catch (IOException e) {
            logger.error("写文件失败");
            logger.error(commentsList.get(0));
            logger.error(commentsList.size());
            e.printStackTrace();
        }
    }

    private SearchResponse scanWeibo(String scollID,Integer size){
        SearchResponse searchResponse = null;
        if (StringUtils.isEmpty(scollID)){
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
            boolQuery.must(QueryBuilders.queryStringQuery("type:Weibo AND weiboId:\"\""));
            searchResponse= elasticsearchTemplate.getClient().prepareSearch()
                    .setQuery(boolQuery).setSize(size)
                    .setIndices(ESConf.INDEX_NAME).setScroll(TimeValue.timeValueMinutes(100))
                    .setSearchType(SearchType.DEFAULT).execute().actionGet();
            return searchResponse;
        }

        //使用上次的scrollId继续访问
        searchResponse = elasticsearchTemplate.getClient().prepareSearchScroll(scollID)
                .setScroll(TimeValue.timeValueMinutes(100))
                .execute()
                .actionGet();
        return searchResponse;
    }

    private SearchResponse scanNews(String scollID,Integer size){
        SearchResponse searchResponse = null;
        if (StringUtils.isEmpty(scollID)){
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
            boolQuery.must(QueryBuilders.termQuery("type","news"));
            searchResponse= elasticsearchTemplate.getClient().prepareSearch()
                    .setQuery(boolQuery).setSize(size)
                    .setIndices(ESConf.INDEX_NAME).setScroll(TimeValue.timeValueMinutes(100))
                    .setSearchType(SearchType.DEFAULT).execute().actionGet();
            return searchResponse;
        }

        //使用上次的scrollId继续访问
        searchResponse = elasticsearchTemplate.getClient().prepareSearchScroll(scollID)
                .setScroll(TimeValue.timeValueMinutes(100))
                .execute()
                .actionGet();
        return searchResponse;
    }


    /**
     * 微博id->weiboId
     * md5（id）->微博id
     * @param limit
     * @return
     */
    @ApiOperation("weiboEtl")
    @RequestMapping(value = "/weiboEtl", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object weiboEtl( @RequestParam Integer limit) {
        long count =0;
        SearchResponse searchResponse = scanWeibo("",limit);
        while (searchResponse.getHits().getHits().length>0){
            SearchHit[] hits = searchResponse.getHits().hits();
            List<JSONObject> commentsList = new ArrayList<>();
            for (int i=0;i<hits.length;i++){
                JSONObject jsonObject = JSONObject.fromObject(hits[i].sourceAsMap());
                jsonObject.put("weiboId",jsonObject.get("id"));
                jsonObject.put("id", MD5Utils.md5(jsonObject.get("id").toString()));
                commentsList.add(jsonObject);
            }
            updateJson("yuqing","article",commentsList);
            count=count+searchResponse.getHits().getHits().length;
            logger.error(searchResponse.getHits().getTotalHits());
            logger.error("处理微博条数："+count+"\n 完成度"+(double)count*100/searchResponse.getHits().getTotalHits()+"%");
            searchResponse = scanWeibo(searchResponse.getScrollId(),limit);
        }
        return "done";
    }

    private SearchResponse scanBbs(String scollID,Integer size){
        SearchResponse searchResponse = null;
        if (StringUtils.isEmpty(scollID)){
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
            boolQuery.must(QueryBuilders.termQuery("type","bbs"));
            searchResponse= elasticsearchTemplate.getClient().prepareSearch()
                    .setQuery(boolQuery).setSize(size)
                    .setIndices(ESConf.INDEX_NAME).setScroll(TimeValue.timeValueMinutes(100))
                    .setSearchType(SearchType.DEFAULT).execute().actionGet();
            return searchResponse;
        }

        //使用上次的scrollId继续访问
        searchResponse = elasticsearchTemplate.getClient().prepareSearchScroll(scollID)
                .setScroll(TimeValue.timeValueMinutes(100))
                .execute()
                .actionGet();
        return searchResponse;
    }


    @ApiOperation("commentsEtl")
    @RequestMapping(value = "/commentsEtl", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object commentsEtl( @RequestParam Integer limit) {
        long count =0;
        long count1 =0;
        SearchResponse searchResponse = scanComments("",limit);
        while (searchResponse.getHits().getHits().length>0){
            SearchHit[] hits = searchResponse.getHits().hits();
            List<JSONObject> commentsList = new ArrayList<>();
            for (int i=0;i<hits.length;i++){
                JSONObject jsonObject = JSONObject.fromObject(hits[i].sourceAsMap());
                commentsList.add(jsonObject);
            }
//              String path = "D:\\data\\comments\\"+"comments"+(count1/50000)+".txt";
            String path = "/home/yuqing/data/esFile/comments/"+"comments"+(count1/50000)+".txt";

            save2File(commentsList,path);
            dleteCommentsDoc("yuqing","comment",commentsList);
            count1=count1+commentsList.size();
            count=count+searchResponse.getHits().getHits().length;
            logger.error("删除comments条数："+count1);
            logger.error("处理comments条数："+count+"\n 完成度"+(double)count*100/searchResponse.getHits().getTotalHits()+"%");
            searchResponse = scanComments(searchResponse.getScrollId(),limit);
        }
        return "done";
    }
    private SearchResponse scanComments(String scollID,Integer size){
        SearchResponse searchResponse = null;
        if (StringUtils.isEmpty(scollID)){
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
            HasParentQueryBuilder hasParentQueryBuilder = QueryBuilders.hasParentQuery(ESConf.TYPE_ARTICLE, QueryBuilders.matchAllQuery());
            boolQuery.must(QueryBuilders.termQuery("type","comments"));
            boolQuery.mustNot(hasParentQueryBuilder);
            searchResponse= elasticsearchTemplate.getClient().prepareSearch()
                    .setQuery(boolQuery).setSize(size)
                    .setIndices(ESConf.INDEX_NAME).setScroll(TimeValue.timeValueMinutes(100))
                    .execute().actionGet();
            return searchResponse;
        }

        //使用上次的scrollId继续访问
        searchResponse = elasticsearchTemplate.getClient().prepareSearchScroll(scollID)
                .setScroll(TimeValue.timeValueMinutes(100))
                .execute()
                .actionGet();
        return searchResponse;
    }

    private void dleteCommentsDoc(String yuqing, String article, List<JSONObject> commentsList) {
        Client client = elasticsearchTemplate.getClient();
        BulkRequestBuilder bulkdel = client.prepareBulk();
        for (int i=0;i<commentsList.size();i++){
            JSONObject jsonObject = commentsList.get(i);
            bulkdel.add(client.prepareDelete(yuqing,article,jsonObject.get("id").toString()).setParent(jsonObject.getString("parent"))
                    .setRouting(jsonObject.getString("parent")).request());
        }
        bulkdel.execute().actionGet();
    }
}
