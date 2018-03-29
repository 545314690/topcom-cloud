package com.topcom.tjs.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.tjs.domain.TjsAccident;
import com.topcom.tjs.service.TjsAccidentManager;
import com.topcom.tjs.vo.KVPair;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/tjsAccident/")
public class TjsAccidentController extends GenericController<
        TjsAccident, Long, TjsAccidentManager> {

    TjsAccidentManager tjsAccidentManager;

    @Autowired
    public void setTjsAccidentManager(TjsAccidentManager tjsAccidentManager) {
        this.tjsAccidentManager = tjsAccidentManager;
        this.manager = this.tjsAccidentManager;
    }

    @RequestMapping(
            value = {"countByGender"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByGender(@RequestParam(required = false) String industryType,@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsAccidentManager.countByAreaAndGender(province,city,industryType);
    }

    @ApiOperation("企业规模")
    @RequestMapping(
            value = {"countByType"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByType(@RequestParam(required = false) String industryType,@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsAccidentManager.countByAreaAndType(province,city,industryType);
    }

    @ApiOperation("行业类别")
    @RequestMapping(
            value = {"countByManageType"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByManageType(@RequestParam(required = false) String industryType,@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsAccidentManager.countByAreaAndManageType(province,city,industryType);
    }

    @ApiOperation("死亡人数及保险")
    @RequestMapping(
            value = {"countByStatusAndInsurance"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByStatusAndInsurance(@RequestParam(required = false) String industryType,@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsAccidentManager.countByStatusAndInsurance(province,city,industryType);
    }

    @ApiOperation("是否是职业伤亡")
    @RequestMapping(
            value = {"countByProfessionDeath"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByProfessionDeath(@RequestParam(required = false) String industryType,@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsAccidentManager.countByProfessionDeath(province,city,industryType);
    }

    @ApiOperation("文化程度")
    @RequestMapping(
            value = {"countByEducation"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByEducation(@RequestParam(required = false) String industryType,@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsAccidentManager.countByEducation(province,city,industryType);
    }


    @ApiOperation("行政隶属关系")
    @RequestMapping(
            value = {"countByAttribute"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByAttribute(@RequestParam(required = false) String industryType,@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsAccidentManager.countByAttribute(province,city,industryType);
    }

    @ApiOperation("处分类别详情")
    @RequestMapping(
            value = {"countByCFLB"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByCFLB(@RequestParam(required = false) String type,@RequestParam(required = false) String industryType,@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsAccidentManager.countByCFLB(type,province,city,industryType);
    }

    @ApiOperation("处分人员详情")
    @RequestMapping(
            value = {"countByCFRY"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByCFRY(@RequestParam(required = false) String industryType,@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsAccidentManager.countByCFRY(province,city,industryType);
    }

}
