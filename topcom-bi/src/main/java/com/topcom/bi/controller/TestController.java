package com.topcom.bi.controller;

import com.topcom.bi.vo.chart.ChartData;
import com.topcom.bi.vo.chart.NameValue;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lism on 17-10-31.
 *
 * @author lism
 */
@RestController
@RequestMapping("test")
public class TestController {


    @ApiOperation("line")
    @RequestMapping(
            value = {"/line"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    public List<ChartData> line() {
        List<ChartData> data = new ArrayList();
        List<NameValue> lineOneData = new ArrayList<>();
        lineOneData.add(new NameValue("2018-03-25",1100));
        lineOneData.add(new NameValue("2018-02-26",2102));
        lineOneData.add(new NameValue("2018-03-27",1900));
        lineOneData.add(new NameValue("2018-02-28",2302));
        lineOneData.add(new NameValue("2018-03-01",1121));
        lineOneData.add(new NameValue("2018-03-02",1121));
        data.add(new ChartData("新闻",lineOneData));
        List<NameValue> lineTowData = new ArrayList<>();
        lineTowData.add(new NameValue("2018-03-25",2100));
        lineTowData.add(new NameValue("2018-02-26",3402));
        lineTowData.add(new NameValue("2018-03-27",2100));
        lineTowData.add(new NameValue("2018-02-28",1302));
        lineTowData.add(new NameValue("2018-03-01",2221));
        lineTowData.add(new NameValue("2018-03-02",3021));
        data.add(new ChartData("微博",lineTowData));
        return data;
    }

}
