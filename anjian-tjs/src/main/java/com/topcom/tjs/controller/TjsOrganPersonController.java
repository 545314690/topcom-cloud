package com.topcom.tjs.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.tjs.domain.TjsOrganPerson;
import com.topcom.tjs.service.TjsOrganPersonManager;
import com.topcom.tjs.vo.KVPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
@RequestMapping("/tjsOrganPerson/")
public class TjsOrganPersonController extends GenericController<
        TjsOrganPerson, Long, TjsOrganPersonManager> {

    TjsOrganPersonManager tjsOrganPersonManager;

    @Autowired
    public void setTjsOrganPersonManager(TjsOrganPersonManager tjsOrganPersonManager) {
        this.tjsOrganPersonManager = tjsOrganPersonManager;
        this.manager = this.tjsOrganPersonManager;
    }
    @RequestMapping(
            value = {"countByLevel"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByLevel(@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsOrganPersonManager.countByAreaAndLevel(province,city);
    }
    @RequestMapping(
            value = {"countByGender"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByGender(@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsOrganPersonManager.countByAreaAndGender(province,city);
    }
    @RequestMapping(
            value = {"countByEducation"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByEducation(@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsOrganPersonManager.countByAreaAndEducation(province,city);
    }
    @RequestMapping(
            value = {"countByAge"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByAge(@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsOrganPersonManager.countByAreaAndAge(province,city);
    }
    @RequestMapping(
            value = {"countByCredentials"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByCredentials(@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsOrganPersonManager.countByAreaAndCredentials(province,city);
    }

    @RequestMapping(
            value = {"findByOrgan"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public Page<TjsOrganPerson> findByOrganId(@RequestParam Long organId,@RequestParam Integer page,@RequestParam Integer limit) {
        return tjsOrganPersonManager.findByOrganId(organId,new PageRequest(page-1,limit));
    }
}
