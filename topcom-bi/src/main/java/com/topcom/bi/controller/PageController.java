package com.topcom.bi.controller;

import com.topcom.bi.domain.Page;
import com.topcom.bi.service.PageService;
import com.topcom.cms.mongo.base.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lism on 17-10-31.
 *
 * @author lism
 */
@RestController
@RequestMapping("page")
public class PageController extends BaseController<Page, String, PageService> {
    private PageService pageService;

    @Autowired
    public void setPageManager(PageService pageService) {
        this.pageService = pageService;
        this.baseService = pageService;
    }

    @ApiOperation("分页根据名字搜索")
    @RequestMapping(
            value = {"/searchByName"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    public org.springframework.data.domain.Page<Page> search( @RequestParam String name,
                                                                  @RequestParam Integer page,@RequestParam Integer limit) {
        return this.pageService.findByNameLike(name, new PageRequest(page-1,limit));
    }

}
