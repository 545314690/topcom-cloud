package com.topcom.tjs.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.tjs.domain.TjsOrgan;
import com.topcom.tjs.service.TjsOrganManager;
import com.topcom.tjs.vo.KVPair;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
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
@Api("监察机构接口")
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
    @ApiOperation("根据区域统计")
    @RequestMapping(
            value = {"countByArea"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<KVPair> countByArea(@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        return tjsOrganManager.countByArea(province,city);
    }
    @ApiOperation("根据区域查找")
    @RequestMapping(
            value = {"findByArea"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<TjsOrgan> findByArea(@RequestParam(required = false) String province, @RequestParam(required = false) String city) {
        if(StringUtils.isBlank(province)){
            return this.tjsOrganManager.findAll();
        }else if(StringUtils.isBlank(city)){
            return tjsOrganManager.findByProvince(province);
        }else {
            return tjsOrganManager.findByProvinceAndCity(province,city);
        }
    }
}
