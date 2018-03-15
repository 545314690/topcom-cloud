package com.topcom.yzswf.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.yzswf.domain.SearchKeyword;
import com.topcom.yzswf.service.SearchKeywordManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/searchKeyword/")
public class SearchKeywordController extends GenericController<
        SearchKeyword, Long, SearchKeywordManager> {

    SearchKeywordManager searchKeywordManager;

    @Autowired
    public void setSearchKeywordManager(SearchKeywordManager searchKeywordManager) {
        this.searchKeywordManager = searchKeywordManager;
        this.manager = this.searchKeywordManager;
    }

    /**
     * 重写父类get方法 加入模糊查询功能
     */
    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/", produces = "application/json")
    @ResponseBody
    public Page<SearchKeyword> get(HttpServletRequest request, HttpServletResponse response) {
        String keyword = request.getParameter("keyword");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        String sortBy = request.getParameter("sortBy");
        String maxWordLen = request.getParameter("maxWordLen");
        if (StringUtils.isNotBlank(page)) {
            this.pageNumber = (Integer.valueOf(page).intValue() - 1);
        } else {
            this.pageNumber = 0;
        }
        if (StringUtils.isNotBlank(limit)) {
            this.pageSize = Integer.valueOf(limit).intValue();
        } else {
            this.pageSize = Integer.valueOf(10).intValue();
        }
        String[] sorters = {};
        if (StringUtils.isBlank(sortBy)) {
            sorters = new String[]{"frequency"};
        } else {
            sorters = sortBy.split(",");
        }
        this.pageable = new PageRequest(this.pageNumber, this.pageSize,
                new Sort(Sort.Direction.DESC, sorters));
        if (StringUtils.isNotBlank(keyword)) {
            this.page = this.manager.findByKeywordLike(this.pageable, keyword.toLowerCase());
        } else if (StringUtils.isNotBlank(maxWordLen)) {
            int maxLength = Integer.valueOf(maxWordLen);
            this.page = this.manager.findByMaxWordLength(this.pageable, maxLength);
        } else {
            this.page = this.manager.findAll(this.pageable);
        }

        logger.info(this.page);
        return this.page;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getRelated", produces = "application/json")
    @ResponseBody
    public List<SearchKeyword> getRelated(@RequestParam String keyword,
                                          @RequestParam(required = false) String limit) {
        if (StringUtils.isBlank(keyword)) {
            return null;
        }
        if (StringUtils.isNotBlank(limit)) {
            this.pageSize = Integer.valueOf(limit).intValue();
        } else {
            this.pageSize = Integer.valueOf(10).intValue();
        }
        return this.manager.getRelated(keyword, this.pageSize);
    }

    /**
     * 更新关键字频率
     *
     * @param kw 关键词，多个用“,”隔开
     */
    @RequestMapping(method = RequestMethod.POST, value = "/updateFrequency")
    public void updateFrequency(HttpServletRequest request, HttpServletResponse response, @RequestParam String kw) {
        this.manager.updateFrequency(kw);

    }

    /**
     * 关键字置顶
     *
     * @param id 关键词id
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/goTop.json")
    public int goTop(@RequestParam Long id, @RequestParam int top) {
        return this.manager.goTop(id, top);
    }
}
