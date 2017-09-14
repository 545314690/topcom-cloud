package com.topcom.cms.yuqing.controller;

import com.mongodb.DBObject;
import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.common.page.PageRequest;
import com.topcom.cms.data.domain.Comments;
import com.topcom.cms.data.domain.ESBaseDomain;
import com.topcom.cms.data.domain.ESConf;
import com.topcom.cms.data.domain.News;
import com.topcom.cms.es.base.ESBaseService;
import com.topcom.cms.es.service.CommentsService;
import com.topcom.cms.es.service.NewsService;
import com.topcom.cms.es.vo.BoolQueryRequest;
import com.topcom.cms.nlp.service.impl.NLPManagerImpl;
import com.topcom.cms.nlp.utils.NLPUtils;
import com.topcom.cms.web.bind.annotation.CurrentUser;
import com.topcom.cms.yuqing.domain.Keywords;
import com.topcom.cms.domain.User;
import com.topcom.cms.yuqing.service.KeywordsManager;
import com.topcom.cms.yuqing.utils.MD5Utils;
import com.topcom.cms.yuqing.utils.NewsIdUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/keywords/")
public class KeywordsController extends
        GenericController<Keywords, Long, KeywordsManager> {
    KeywordsManager keywordsManager;

    @Autowired
    NLPManagerImpl nlpManager;

    @Autowired
    protected ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    CommentsService commentsService;

    @Autowired
    NewsService newsService;

    @Autowired
    public void setKeywordsManager(KeywordsManager keywordsManager) {
        this.keywordsManager = keywordsManager;
        this.manager = keywordsManager;
    }

    /**
     * 查询当前登录用户的Keywords
     */
    @ApiOperation("根据关键词类型查询")
    @RequestMapping(value = "/findByType", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Keywords findByUser(@CurrentUser User user, @RequestParam Keywords.Type type) {
        List<Keywords> keywordsList = keywordsManager.findByUserIdAndType(user.getId(), type);
        if (keywordsList.size() > 0) {
            return keywordsList.get(0);
        } else {
            return keywordsManager.save(new Keywords(type, user.getId()));
        }
    }


    @ApiOperation("根据id查询词云")
    @RequestMapping(
            value = {"hotkeywords"},
            method = {RequestMethod.GET}
    )
    public Object hotKeywords(HttpServletRequest request, HttpServletResponse response,
                              @ApiParam("id") @RequestParam(required = false) String id,
                              @ApiParam("count") @RequestParam(required = false) Integer count) {
        ESBaseDomain esBaseDomain = newsService.findById(id);
        return this.nlpManager.keywords(esBaseDomain.getTitle() + esBaseDomain.getContent(), count);
    }



}
