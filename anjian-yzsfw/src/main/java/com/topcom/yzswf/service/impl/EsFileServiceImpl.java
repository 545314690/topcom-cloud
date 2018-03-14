package com.topcom.yzswf.service.impl;

import com.topcom.cms.common.page.DateParam;
import com.topcom.cms.common.page.Order;
import com.topcom.cms.common.page.PageRequest;
import com.topcom.yzswf.config.EsConf;
import com.topcom.yzswf.service.ElasticSearchService;
import com.topcom.yzswf.service.EsFileService;
import com.topcom.yzswf.util.QueryWord;
import com.topcom.yzswf.util.es.ESQueryBuilderConstructor;
import com.topcom.yzswf.util.es.ESQueryBuilders;
import com.topcom.yzswf.vo.EsIndexFile;
import com.topcom.yzswf.vo.FileQueryVO;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lsm on 2018/3/13 0013.
 */
@Component
public class EsFileServiceImpl implements EsFileService {
    @Autowired
    private ElasticSearchService elasticSearchService;
    @Override
    public void index2Es(EsIndexFile esIndexFile) throws Exception {
        elasticSearchService.insertDataWithPipeline(EsConf.INDEX,EsConf.TYPE,EsConf.PIPELINE, JSONObject.fromObject(esIndexFile).toString());
    }

    @Override
    public com.topcom.yzswf.util.Page search(FileQueryVO fileQueryVO) throws Exception {
        String searchType = fileQueryVO.getSearchType();
        if(StringUtils.isNotBlank(searchType)){
            if("content".equals(searchType)){
                searchType = "attachment.content";
            }
        }else{
            searchType = "filename";
        }
        PageRequest pageRequest = fileQueryVO.getPageRequest();
        ESQueryBuilderConstructor constructor = new ESQueryBuilderConstructor();
        QueryWord queryWord = fileQueryVO.getQueryWord();
        if(queryWord !=null){
            constructor.must(new ESQueryBuilders().queryString(searchType + ":" + queryWord.toQueryString()));
        }
        constructor.setSize(pageRequest.getLimit());  //查询返回条数，最大 10000
        constructor.setFrom(pageRequest.getLimit() * (pageRequest.getPage()-1));  //分页查询条目起始位置， 默认0
        List<Order> orders = pageRequest.getOrders();

        if(StringUtils.isNotBlank(fileQueryVO.getType())){
            constructor.must(new ESQueryBuilders().term("type",fileQueryVO.getType()));
        }
        if(StringUtils.isNotBlank(fileQueryVO.getSource())){
            constructor.must(new ESQueryBuilders().term("source",fileQueryVO.getSource()));
        }
        if(CollectionUtils.isNotEmpty(fileQueryVO.getFileTypes())){
            constructor.must(new ESQueryBuilders().terms("fileType",fileQueryVO.getFileTypes()));
        }
        if(StringUtils.isNotBlank(fileQueryVO.getAccidentType())){
            constructor.must(new ESQueryBuilders().term("accidentType",fileQueryVO.getAccidentType()));
        }
        DateParam dateParam = fileQueryVO.getDateParam();
        if(dateParam != null){
            Date startDate = dateParam.startDate();
            Date endDate = dateParam.endDate();
            constructor.must(new ESQueryBuilders().range("uploadTime",startDate.getTime(),endDate.getTime()));
        }
        if(orders != null && orders.size()>0){
            Order order = orders.get(0);
            Sort.Direction direction = order.getDirection();
            if(Sort.Direction.ASC.equals(direction)){
                constructor.setAsc(order.getOrderBy()); //排序
            }else{
                constructor.setDesc(order.getOrderBy()); //排序
            }
        }
//
        com.topcom.yzswf.util.Page page = elasticSearchService.search(EsConf.INDEX,EsConf.TYPE, constructor);
        return page;
    }

    @Override
    public Map<String, Object> findById(String id) throws Exception {
        return elasticSearchService.findById(EsConf.INDEX,EsConf.TYPE,id);
    }

    @Override
    public void downloadTimesPlusOne(String id) throws Exception {
        Map<String, Object> map = this.findById(id);
        if(map != null && map.size()>0){
            Integer downloadTimes = (Integer)map.get("downloadTimes");
            downloadTimes +=1;
            map.put("downloadTimes",downloadTimes);
            this.elasticSearchService.updateData(EsConf.INDEX,EsConf.TYPE,id,JSONObject.fromObject(map).toString());
        }
    }
}
