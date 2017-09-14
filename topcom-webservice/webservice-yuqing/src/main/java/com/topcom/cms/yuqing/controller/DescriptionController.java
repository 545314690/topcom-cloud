package com.topcom.cms.yuqing.controller;

import com.topcom.cms.es.vo.AggRequest;
import com.topcom.cms.es.vo.BoolQueryRequest;
import com.topcom.cms.es.vo.KV;
import com.topcom.cms.es.vo.OpinionAndTime;
import com.topcom.cms.yuqing.service.DescriptionManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by maxl on 17-6-2.
 */
@RestController
@RequestMapping(value = "/description")
@Api(value = "描述接口", description = "描述接口API")
public class DescriptionController {

    @Autowired
    DescriptionManager descriptionManager;

    @ApiOperation("省份所属地区查询")
    @RequestMapping(
            value = {"/getProvinceLocality.json"},
            method = {RequestMethod.GET},
            produces = {"application/json"})
    public Object getProvinceLocality(HttpServletRequest request, HttpServletResponse response,
                                     @ApiParam("省，逗号分割") @RequestParam(required = false) String province){

        return descriptionManager.getProvinceLocality(province);
    }

    @ApiOperation("当月与上月舆情波动情况对比")
    @RequestMapping(value = {"/trendOfOpinion"}, method = {RequestMethod.POST})
    public Object trendOfOpinion(HttpServletRequest request, HttpServletResponse response,
                                      @ApiParam("当月与前一个月每日的舆情数量") @RequestBody OpinionAndTime opinionAndTime) {

        return descriptionManager.trendOfOpinion(opinionAndTime.getMonthNum(),opinionAndTime.getLastMonthNum());
    }

    @ApiOperation("当月舆情超出平均数30%与50%的时间")
    @RequestMapping(value = {"/outOFAve"}, method = {RequestMethod.POST})
    public Object outOFAve(HttpServletRequest request, HttpServletResponse response,
                                 @ApiParam("当月每日舆情数量") @RequestBody OpinionAndTime opinionAndTime) {

        return descriptionManager.outOFAve(opinionAndTime.getMonthNum());
    }


    @ApiOperation("月报摘要")
    @RequestMapping(value = {"/monthLyOutline"}, method = {RequestMethod.POST})
    public Object monthLyOutline(HttpServletRequest request, HttpServletResponse response,
                                      @ApiParam("文章关键词、开始结束时间") @RequestBody BoolQueryRequest boolQueryRequest) {

        return descriptionManager.monthLyOutline(boolQueryRequest);
    }
    @ApiOperation("周报摘要")
    @RequestMapping(value = {"/weeklyOutline"}, method = {RequestMethod.POST})
    public Object weeklyOutline(HttpServletRequest request, HttpServletResponse response,
                                 @ApiParam("文章关键词、开始结束时间") @RequestBody BoolQueryRequest boolQueryRequest) {

        return descriptionManager.weeklyOutline(boolQueryRequest);
    }

    @ApiOperation("专报摘要")
    @RequestMapping(value = {"/specialOutline"}, method = {RequestMethod.POST})
    public Object specialOutline(HttpServletRequest request, HttpServletResponse response,
                                 @ApiParam("文章关键词、开始结束时间") @RequestBody BoolQueryRequest boolQueryRequest) {

        return descriptionManager.specialOutline(boolQueryRequest);
    }
}
