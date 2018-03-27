package com.topcom.tjs.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.tjs.domain.TjsEnforcement;
import com.topcom.tjs.service.TjsEnforcementManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lism
 * @date 2018/3/26 0026
 */
@Controller
@RequestMapping("/tjsEnforcement/")
public class TjsEnforcementController extends GenericController<
        TjsEnforcement, Long, TjsEnforcementManager> {

    TjsEnforcementManager tjsEnforcementManager;

    @Autowired
    public void setTjsEnforcementManager(TjsEnforcementManager tjsEnforcementManager) {
        this.tjsEnforcementManager = tjsEnforcementManager;
        this.manager = this.tjsEnforcementManager;
    }

}
