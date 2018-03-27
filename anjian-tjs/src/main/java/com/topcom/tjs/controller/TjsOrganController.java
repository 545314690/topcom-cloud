package com.topcom.tjs.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.tjs.domain.TjsOrgan;
import com.topcom.tjs.service.TjsOrganManager;
import com.topcom.tjs.vo.KVPair;
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
    @RequestMapping(
            value = {"countByArea"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByArea(@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsOrganManager.countByArea(province,city);
    }
}
