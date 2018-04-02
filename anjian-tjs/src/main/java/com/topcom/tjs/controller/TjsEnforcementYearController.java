package com.topcom.tjs.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.tjs.domain.TjsEnforcementYear;
import com.topcom.tjs.domain.TjsOrgan;
import com.topcom.tjs.service.TjsEnforcementYearManager;
import com.topcom.tjs.service.TjsOrganManager;
import com.topcom.tjs.vo.KVPair;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
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
@Api("执法统计")
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
}
