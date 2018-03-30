package com.topcom.tjs.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.tjs.domain.TjsOrganPerson;
import com.topcom.tjs.service.TjsOrganPersonManager;
import com.topcom.tjs.vo.KVPair;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
@Api("监察人员接口")
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
    @ApiOperation("级别统计")
    @RequestMapping(
            value = {"countByLevel"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByLevel(@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsOrganPersonManager.countByAreaAndLevel(province,city);
    }
    @ApiOperation("性别统计")
    @RequestMapping(
            value = {"countByGender"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByGender(@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsOrganPersonManager.countByAreaAndGender(province,city);
    }
    @ApiOperation("教育程度统计")
    @RequestMapping(
            value = {"countByEducation"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByEducation(@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsOrganPersonManager.countByAreaAndEducation(province,city);
    }
    @ApiOperation("年龄段统计")
    @RequestMapping(
            value = {"countByAge"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByAge(@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsOrganPersonManager.countByAreaAndAge(province,city);
    }
    @ApiOperation("有无证件统计")
    @RequestMapping(
            value = {"countByCredentials"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByCredentials(@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsOrganPersonManager.countByAreaAndCredentials(province,city);
    }
    @ApiOperation("根据机构查询")
    @RequestMapping(
            value = {"findByOrgan"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public Page<TjsOrganPerson> findByOrganId(@RequestParam Long organId,@RequestParam Integer page,@RequestParam Integer limit) {
        if(page < 1){
            page = 1;
        }
        if(limit<1){
            limit = 10;
        }
        Pageable pageable =new PageRequest(page-1,limit);
        return tjsOrganPersonManager.findByOrganId(organId,pageable);
    }
}
