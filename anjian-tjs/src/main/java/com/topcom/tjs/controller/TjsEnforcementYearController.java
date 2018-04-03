package com.topcom.tjs.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.tjs.domain.TjsEnforcementYear;
import com.topcom.tjs.service.TjsEnforcementYearManager;
import com.topcom.tjs.vo.TBHB;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
@Api("执法年度统计")
@Controller
@RequestMapping("/tjsEnforcementYear/")
public class TjsEnforcementYearController extends GenericController<
        TjsEnforcementYear, Long, TjsEnforcementYearManager> {

    TjsEnforcementYearManager tjsEnforcementYearManager;

    @Autowired
    public void setTjsEnforcementYearManager(TjsEnforcementYearManager tjsEnforcementYearManager) {
        this.tjsEnforcementYearManager = tjsEnforcementYearManager;
        this.manager = this.tjsEnforcementYearManager;
    }

    /**
     *
     * @param startYear 开始年份
     * @param endYear 结束年份
     * @param keyTrades 重点行业
     * @return
     */
    @ApiOperation("countByMetricName")
    @RequestMapping(
            value = {"countByMetricName"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<TBHB> countByArea(@RequestParam Integer startYear, @RequestParam Integer endYear, @RequestParam(required = false) String keyTrades) {
        return tjsEnforcementYearManager.countByMetricName(startYear,endYear,keyTrades);
    }
}
