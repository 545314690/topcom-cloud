package com.topcom.tjs.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.common.page.DateParam;
import com.topcom.tjs.domain.TjsAccident;
import com.topcom.tjs.service.TjsAccidentManager;
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
@Api("事故接口")
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
    public List<KVPair> countByGender(@RequestParam String startDate,@RequestParam String endDate,@RequestParam(required = false) String industryType,@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
         
        return tjsAccidentManager.countByAreaAndGender( startDate,endDate,province,city,industryType);
    }

    @ApiOperation("地区统计")
    @RequestMapping(
            value = {"countByArea"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByArea(@RequestParam String startDate,@RequestParam String endDate,@RequestParam(required = false) String industryType,@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
         
        return tjsAccidentManager.countByArea( startDate,endDate,province,city,industryType);
    }
    @ApiOperation("企业规模")
    @RequestMapping(
            value = {"countByType"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByType(@RequestParam String startDate,@RequestParam String endDate,@RequestParam(required = false) String industryType,@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
         
        return tjsAccidentManager.countByAreaAndType( startDate,endDate,province,city,industryType);
    }

    @ApiOperation("行业类别")
    @RequestMapping(
            value = {"countByManageType"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByManageType(@RequestParam String startDate,@RequestParam String endDate,@RequestParam(required = false) String industryType,@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
         
        return tjsAccidentManager.countByAreaAndManageType( startDate,endDate,province,city,industryType);
    }

    @ApiOperation("死亡人数及保险")
    @RequestMapping(
            value = {"countByStatusAndInsurance"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByStatusAndInsurance(@RequestParam String startDate,@RequestParam String endDate,@RequestParam(required = false) String industryType,@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
         
        return tjsAccidentManager.countByStatusAndInsurance( startDate,endDate,province,city,industryType);
    }

    @ApiOperation("是否是职业伤亡")
    @RequestMapping(
            value = {"countByProfessionDeath"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByProfessionDeath(@RequestParam String startDate,@RequestParam String endDate,@RequestParam(required = false) String industryType,@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
         
        return tjsAccidentManager.countByProfessionDeath( startDate,endDate,province,city,industryType);
    }

    @ApiOperation("文化程度")
    @RequestMapping(
            value = {"countByEducation"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByEducation(@RequestParam String startDate,@RequestParam String endDate,@RequestParam(required = false) String industryType,@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
         
        return tjsAccidentManager.countByEducation( startDate,endDate,province,city,industryType);
    }


    @ApiOperation("行政隶属关系")
    @RequestMapping(
            value = {"countByAttribute"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByAttribute(@RequestParam String startDate,@RequestParam String endDate,@RequestParam(required = false) String industryType,@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
         
        return tjsAccidentManager.countByAttribute( startDate,endDate,province,city,industryType);
    }

    @ApiOperation("处分类别详情")
    @RequestMapping(
            value = {"countByCFLB"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByCFLB(@RequestParam String startDate,@RequestParam String endDate,@RequestParam(required = false) String type,@RequestParam(required = false) String industryType,@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
         
        return tjsAccidentManager.countByCFLB( startDate,endDate,type,province,city,industryType);
    }

    @ApiOperation("处分人员详情")
    @RequestMapping(
            value = {"countByCFRY"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByCFRY(@RequestParam String startDate,@RequestParam String endDate,@RequestParam(required = false) String industryType,@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
         
        return tjsAccidentManager.countByCFRY( startDate,endDate,province,city,industryType);
    }
    @ApiOperation("根据企业id查找")
    @RequestMapping(
            value = {"findByCompany"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public Page<TjsAccident> findByCompanyId(@RequestParam Long companyId, @RequestParam Integer page, @RequestParam Integer limit,
                                             @RequestParam String startDate, @RequestParam String endDate) {
        Pageable pageable= new PageRequest(page-1,limit);
        DateParam dateParam = new DateParam(startDate, endDate);
        return tjsAccidentManager.findByCompanyIdAndHappenedTimeBetween(companyId, dateParam.startDate(),dateParam.endDate(),pageable);
    }
}
