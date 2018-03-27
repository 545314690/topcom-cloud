package com.topcom.tjs.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.tjs.domain.TjsOrgan;
import com.topcom.tjs.service.TjsOrganManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
@Controller
@RequestMapping("/tjsOrgan/")
public class TjsOrganController extends GenericController<
        TjsOrgan, Long, TjsOrganManager> {

    TjsOrganManager tjsOrganManager;

    @Autowired
    public void setTjsOrganManager(TjsOrganManager tjsOrganManager) {
        this.tjsOrganManager = tjsOrganManager;
        this.manager = this.tjsOrganManager;
    }

}
