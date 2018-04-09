package com.topcom.tjs.create;


import com.topcom.tjs.domain.TjsEnforcement;
import com.topcom.tjs.domain.TjsEnforcementYear;
import com.topcom.tjs.domain.TjsOrgan;
import com.topcom.tjs.service.TjsEnforcementManager;
import com.topcom.tjs.service.TjsEnforcementYearManager;
import com.topcom.tjs.service.TjsOrganManager;
import com.topcom.tjs.utils.CreateDataUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 执法统计数据
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CreatetEnforcementYear {

    List<String> nameList = CreateDataUtil.getRandomName();
    String[] metricName = new String[]{"安全监管监察年度执法计划合计,家（矿）次","专项,家（矿）次"
            ,"听证会次数,次","行政复议起数,起","行政诉讼起数,起"
            ,"辖区内重大危险源,处"};
    Random random = new Random();
    String[] keyTrades = new String[]{"煤矿","金属非金属矿山","化工","烟花爆竹","冶金","规模以上其他工贸生产经营单位",
            "规模以上其他商贸制造业","其他"};
    @Autowired
    TjsEnforcementYearManager tjsEnforcementYearManager;

    @Autowired
    TjsOrganManager organManager;
    @Test
    public void create(){
        //List<TjsOrgan> all = organManager.findAll();
        List<TjsEnforcementYear> ylist = new ArrayList<>();
        for(int i=2011;i<2019;i++){

//            for (TjsOrgan organ:all){
                for (String trade:keyTrades){
                    for (String metric:metricName){
                        TjsEnforcementYear year = new TjsEnforcementYear();
                        year.setValue(random.nextInt(10000));
                        if (metric.equals("辖区内重大危险源,处")){
                            TjsEnforcementYear year1 = new TjsEnforcementYear();
                            year1.setValue(CreateDataUtil.getGaussianRandom(0,year.getValue()));
                            year1.setUnit("处");
                            year1.setMetricName("实际监控");
                            year1.setFZR(nameList.get(random.nextInt(nameList.size()-1)));
                            year1.setKeyTrades(trade);
                            year1.setTBR(nameList.get(random.nextInt(nameList.size()-1)));
                            year1.setYear(i);
                            ylist.add(year1);
                            TjsEnforcementYear year2 = new TjsEnforcementYear();
                            int value = year1.getValue()*100/year.getValue();
                            year2.setValue(value);
                            year2.setUnit("%");
                            year2.setMetricName("监控率");
                            year2.setFZR(nameList.get(random.nextInt(nameList.size()-1)));
                            year2.setKeyTrades(trade);
                            year2.setTBR(nameList.get(random.nextInt(nameList.size()-1)));
                            year2.setYear(i);
                            ylist.add(year2);
                        }
                        year.setUnit(metric.split(",")[1]);
                        year.setMetricName(metric.split(",")[0]);
                        year.setFZR(nameList.get(random.nextInt(nameList.size()-1)));
                        year.setKeyTrades(trade);
                        year.setTBR(nameList.get(random.nextInt(nameList.size()-1)));
                        year.setYear(i);
                        ylist.add(year);
                    }
                }
//            }
            tjsEnforcementYearManager.save(ylist);
        }

    }

}
