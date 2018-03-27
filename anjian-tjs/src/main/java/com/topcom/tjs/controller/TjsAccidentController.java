package com.topcom.tjs.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.tjs.domain.TjsAccident;
import com.topcom.tjs.service.TjsAccidentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
