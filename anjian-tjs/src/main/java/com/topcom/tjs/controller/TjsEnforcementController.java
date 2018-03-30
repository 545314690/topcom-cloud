package com.topcom.tjs.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.common.page.DateParam;
import com.topcom.tjs.domain.TjsEnforcement;
import com.topcom.tjs.service.TjsEnforcementManager;
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

import java.util.*;

/**
 * @author lism
 * @date 2018/3/26 0026
 */
@Api("执法情况接口")
@Controller
@RequestMapping("/tjsEnforcement/")
public class TjsEnforcementController extends GenericController<
        TjsEnforcement, Long, TjsEnforcementManager> {

    TjsEnforcementManager tjsEnforcementManager;

    @Autowired
    public void setTjsEnforcementManager(TjsEnforcementManager tjsEnforcementManager) {
        this.tjsEnforcementManager = tjsEnforcementManager;
        this.manager = this.tjsEnforcementManager;
    }
    @ApiOperation("根据企业id查找")
    @RequestMapping(
            value = {"findByCompany"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public Page<TjsEnforcement> findByCompanyId(@RequestParam Long companyId, @RequestParam Integer page, @RequestParam Integer limit,
                                             @RequestParam String startDate, @RequestParam String endDate) {
        Pageable pageable= new PageRequest(page-1,limit);
        DateParam dateParam = new DateParam(startDate, endDate);
        return tjsEnforcementManager.findByCompanyIdAndZFJCJZSJBetween(companyId,dateParam.startDate(),dateParam.endDate(),pageable);
    }
    @ApiOperation("企业违法违规、隐患、整改、统计")
    @RequestMapping(
            value = {"countByCompany"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByCompany(@RequestParam Long companyId,@RequestParam String startDate, @RequestParam String endDate) {
        //TODO:可改为一次查询，避免多次
        Map<String,String> map = new HashMap();
        map.put("查处一般事故隐患（项）","CCYBSGYHX");
        map.put("查处重大事故隐患（项）","CCZDSGYHX");
        map.put("查处安全生产违法违规行为（项）","CCAQSCWFWGXWX");
        map.put("已整改安全生产违法违规行为（项）","YZGAQSCWFWGXW");
        map.put("已整改一般事故隐患（项）","YZGYBSGYH");
        map.put("已整改重大事故隐患（项）","YZGZDSGYH");
        List<KVPair> kvPairList = new ArrayList<>();
        Set<String> keySet = map.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            String value = map.get(key);
            KVPair kvPair = tjsEnforcementManager.countByCompanyIdAndDateAndProperty(companyId,startDate,endDate,key,value);
            kvPairList.add(kvPair);
        }
        return kvPairList;
    }

    @ApiOperation("执法检查类别分析")
    @RequestMapping(
            value = {"countByCategory"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByCategory(@RequestParam String startDate,@RequestParam String endDate,
                                        @RequestParam(required = false) String industryType,
                                        @RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsEnforcementManager.countByDateAndAreaAndIndustryTypeAndCategory(startDate,endDate,industryType,province,city);
    }
    @ApiOperation("是否为随机抽取检查单位")
    @RequestMapping(
            value = {"countByRandomCheck"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByRandomCheck(@RequestParam String startDate,@RequestParam String endDate,
                                        @RequestParam(required = false) String industryType,
                                        @RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsEnforcementManager.countByDateAndAreaAndIndustryTypeAndRandomCheck(startDate,endDate,industryType,province,city);
    }
    @ApiOperation("是否整改复查")
    @RequestMapping(
            value = {"countByReCheck"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByReCheck(@RequestParam String startDate,@RequestParam String endDate,
                                        @RequestParam(required = false) String industryType,
                                        @RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsEnforcementManager.countByDateAndAreaAndIndustryTypeAndReCheck(startDate,endDate,industryType,province,city);
    }
    @ApiOperation("是否含职业卫生执法检查")
    @RequestMapping(
            value = {"countByHealth"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByHealth(@RequestParam String startDate,@RequestParam String endDate,
                                        @RequestParam(required = false) String industryType,
                                        @RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsEnforcementManager.countByDateAndAreaAndIndustryTypeAndHealth(startDate,endDate,industryType,province,city);
    }
    @ApiOperation("是否举报核实执法检查")
    @RequestMapping(
            value = {"countByReport"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByReport(@RequestParam String startDate,@RequestParam String endDate,
                                        @RequestParam(required = false) String industryType,
                                        @RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsEnforcementManager.countByDateAndAreaAndIndustryTypeAndReport(startDate,endDate,industryType,province,city);
    }
    @ApiOperation("罚款金额、隐患、违法行为")
    @RequestMapping(
            value = {"countByFine"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public Map<String, Object> countByFine(@RequestParam String startDate,@RequestParam String endDate,
                                        @RequestParam(required = false) String industryType,
                                        @RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsEnforcementManager.sumByDateAndAreaAndIndustryTypeAndFine(startDate,endDate,industryType,province,city);
    }
}
