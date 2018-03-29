package com.topcom.tjs.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.tjs.domain.TjsSpecialCompany;
import com.topcom.tjs.service.TjsSpecialCompanyManager;
import com.topcom.tjs.vo.KVPair;
import io.swagger.annotations.Api;
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
@Api("企业接口")
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
    @ApiOperation("行政隶属统计")
    @RequestMapping(
            value = {"countByArea"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByArea(@RequestParam(required = false) String industryType,@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsSpecialCompanyManager.countByAreaAndIndustryType(industryType,province,city);
    }
    @ApiOperation("根据区域查询")
    @RequestMapping(
            value = {"findByArea"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<TjsSpecialCompany> findByArea(@RequestParam(required = false) String industryType,@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return this.tjsSpecialCompanyManager.findByArea(industryType,province,city);
    }
    @ApiOperation("规模统计")
    @RequestMapping(
            value = {"countByScale"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByScale(@RequestParam(required = false) String industryType,@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsSpecialCompanyManager.countByAreaAndIndustryTypeAndScale(industryType,province,city);
    }

    /**
     *
     * @param industryType
     * @param province
     * @param city
     * @return
     */
    @ApiOperation("行政隶属关系统计")
    @RequestMapping(
            value = {"countByAdministrative"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByAdministrative(@RequestParam(required = false) String industryType,@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsSpecialCompanyManager.countByAreaAndIndustryTypeAndAdministrative(industryType,province,city);
    }
    @ApiOperation("生产经营方式统计")
    @RequestMapping(
            value = {"countByProductType"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByProductType(@RequestParam(required = false) String industryType,@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsSpecialCompanyManager.countByAreaAndIndustryTypeAndProductType(industryType,province,city);
    }
    @ApiOperation("有无证件统计")
    @RequestMapping(
            value = {"countByLicence"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByLicence(@RequestParam(required = false) String industryType,@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsSpecialCompanyManager.countByAreaAndIndustryTypeAndLicence(industryType,province,city);
    }
    @ApiOperation("证件到期统计")
    @RequestMapping(
            value = {"countByLicenceDate"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByLicenceDate(@RequestParam(required = false) String industryType,@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsSpecialCompanyManager.countByAreaAndIndustryTypeAndcountByLicenceDate(industryType,province,city);
    }
}
