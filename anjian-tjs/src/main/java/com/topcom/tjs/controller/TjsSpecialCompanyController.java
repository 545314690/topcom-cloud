package com.topcom.tjs.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.tjs.domain.TjsSpecialCompany;
import com.topcom.tjs.service.TjsSpecialCompanyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
@Controller
@RequestMapping("/tjsSpecialCompany/")
public class TjsSpecialCompanyController extends GenericController<
        TjsSpecialCompany, Long, TjsSpecialCompanyManager> {

    TjsSpecialCompanyManager tjsSpecialCompanyManager;

    @Autowired
    public void setTjsSpecialCompanyManager(TjsSpecialCompanyManager tjsSpecialCompanyManager) {
        this.tjsSpecialCompanyManager = tjsSpecialCompanyManager;
        this.manager = this.tjsSpecialCompanyManager;
    }

}
