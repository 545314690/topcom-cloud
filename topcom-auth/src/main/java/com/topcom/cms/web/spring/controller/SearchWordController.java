package com.topcom.cms.web.spring.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.domain.SearchWord;
import com.topcom.cms.service.SearchWordManager;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 */
@Controller
@RequestMapping("/searchWord")
public class SearchWordController extends GenericController<SearchWord, Long, SearchWordManager> {

    private SearchWordManager searchWordManager;

    @Autowired
    public void setSearchWordManager(SearchWordManager searchWordManager) {
        this.searchWordManager = searchWordManager;
        this.manager = this.searchWordManager;
    }


    /**
     * 增加点击次数 对于单个搜索词 可以支持累加
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/add", produces = "application/json")
    @ResponseBody
    public SearchWord addClickCount(@ApiParam("Word") @RequestParam(required = true) String word,
                                    @ApiParam("type") @RequestParam(required = true) Integer type){
        SearchWord result = searchWordManager.addClickCount(word,type);
        return this.searchWordManager.save(result);
    }


    /**
     * 热词查询 查询排名前 limit 的word
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/findByType", produces = "application/json")
    @ResponseBody
    public List<SearchWord> findByType(@ApiParam("limit") @RequestParam(required = true) Integer limit,
                                       @ApiParam("type") @RequestParam(required = true) Integer type){

        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC,"wordCount"));
        Pageable page = new PageRequest(0,limit,sort);
        Page<SearchWord> searchWords  = this.searchWordManager.findByType(page,type);

        return searchWords.getContent();
    }
}
