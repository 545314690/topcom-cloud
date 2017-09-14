package com.topcom.cms.yuqing.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.data.domain.ESBaseDomain;
import com.topcom.cms.domain.User;
import com.topcom.cms.es.service.NewsService;
import com.topcom.cms.nlp.service.impl.NLPManagerImpl;
import com.topcom.cms.web.bind.annotation.CurrentUser;
import com.topcom.cms.yuqing.domain.KeywordsWeight;
import com.topcom.cms.yuqing.service.KeywordsWeightManager;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/keywordsWeight/")
public class KeywordsWeightController extends
        GenericController<KeywordsWeight, Long, KeywordsWeightManager> {
    KeywordsWeightManager keywordsWeightManager;

    @Autowired
    NLPManagerImpl nlpManager;


    @Autowired
    NewsService newsService;

    @Autowired
    public void setKeywordsManager(KeywordsWeightManager keywordsWeightManager) {
        this.keywordsWeightManager = keywordsWeightManager;
        this.manager = keywordsWeightManager;
    }

    /**
     * 查询当前登录用户的Keywords
     */
    @ApiOperation("根据关键词类型查询")
    @RequestMapping(value = "/findByType", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public KeywordsWeight findByUser(@CurrentUser User user, @RequestParam KeywordsWeight.Type type) {
        List<KeywordsWeight> keywordsList = keywordsWeightManager.findByUserIdAndType(user.getId(), type);
        if (keywordsList.size() > 0) {
            return keywordsList.get(0);
        } else {
            return keywordsWeightManager.save(new KeywordsWeight(type, user.getId()));
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
