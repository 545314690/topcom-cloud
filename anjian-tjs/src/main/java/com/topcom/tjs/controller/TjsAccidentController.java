package com.topcom.tjs.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.common.page.DateParam;
import com.topcom.tjs.domain.TjsAccident;
import com.topcom.tjs.service.TjsAccidentManager;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
@Controller
@RequestMapping("/tjsAccident/")
public class TjsAccidentController extends GenericController<
        TjsAccident, Long, TjsAccidentManager> {

    TjsAccidentManager tjsAccidentManager;

    @Autowired
    public void setTjsAccidentManager(TjsAccidentManager tjsAccidentManager) {
        this.tjsAccidentManager = tjsAccidentManager;
        this.manager = this.tjsAccidentManager;
    }
    @ApiOperation("根据企业id查找")
    @RequestMapping(
            value = {"findByCompany"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public Page<TjsAccident> findByCompanyId(@RequestParam Long companyId, @RequestParam Integer page, @RequestParam Integer limit,
                                             @RequestParam String startDate, @RequestParam String endDate) {
        Pageable pageable= new PageRequest(page-1,limit);
        DateParam dateParam = new DateParam(startDate, endDate);
        return tjsAccidentManager.findByCompanyIdAndHappenedTimeBetween(companyId,dateParam.startDate(),dateParam.endDate(),pageable);
    }
}
