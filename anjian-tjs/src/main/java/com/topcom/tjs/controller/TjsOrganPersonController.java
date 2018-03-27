package com.topcom.tjs.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.tjs.domain.TjsAccident;
import com.topcom.tjs.domain.TjsOrganPerson;
import com.topcom.tjs.service.TjsAccidentManager;
import com.topcom.tjs.service.TjsOrganPersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
@Controller
@RequestMapping("/tjsOrganPerson/")
public class TjsOrganPersonController extends GenericController<
        TjsOrganPerson, Long, TjsOrganPersonManager> {

    TjsOrganPersonManager tjsOrganPersonManager;

    @Autowired
    public void setTjsOrganPersonManager(TjsOrganPersonManager tjsOrganPersonManager) {
        this.tjsOrganPersonManager = tjsOrganPersonManager;
        this.manager = this.tjsOrganPersonManager;
    }

}
