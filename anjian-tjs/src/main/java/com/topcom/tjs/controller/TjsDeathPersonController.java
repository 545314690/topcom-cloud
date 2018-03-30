package com.topcom.tjs.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.tjs.domain.TjsDeathPerson;
import com.topcom.tjs.service.TjsDeathPersonManager;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
@Api("事故伤亡人员接口")
@Controller
@RequestMapping("/tjsDeathPerson/")
public class TjsDeathPersonController extends GenericController<
        TjsDeathPerson, Long, TjsDeathPersonManager> {

    TjsDeathPersonManager tjsDeathPersonManager;

    @Autowired
    public void setTjsDeathPersonManager(TjsDeathPersonManager tjsDeathPersonManager) {
        this.tjsDeathPersonManager = tjsDeathPersonManager;
        this.manager = this.tjsDeathPersonManager;
    }

}
