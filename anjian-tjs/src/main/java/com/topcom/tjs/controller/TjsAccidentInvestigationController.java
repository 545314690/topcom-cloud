package com.topcom.tjs.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.tjs.domain.TjsAccidentInvestigation;
import com.topcom.tjs.service.TjsAccidentInvestigationManager;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lism
 * @date 2018/3/26 0026
 */
@Api("生产安全事故调查处理及责任追究情况接口")
@Controller
@RequestMapping("/tjsAccidentInvestigation/")
public class TjsAccidentInvestigationController extends GenericController<
        TjsAccidentInvestigation, Long, TjsAccidentInvestigationManager> {

    TjsAccidentInvestigationManager tjsAccidentInvestigationManager;

    @Autowired
    public void setTjsAccidentInvestigationManager(TjsAccidentInvestigationManager tjsAccidentInvestigationManager) {
        this.tjsAccidentInvestigationManager = tjsAccidentInvestigationManager;
        this.manager = this.tjsAccidentInvestigationManager;
    }

}
