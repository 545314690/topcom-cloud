package com.topcom.tjs.create;


import com.topcom.tjs.domain.TjsEnforcementYear;
import com.topcom.tjs.service.TjsEnforcementYearManager;
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
    String[] metricName = new String[]{"安全监管监察年度执法计划","安全监管监察年度执法计划合计","专项"
            ,"听证会次数","行政复议起数","行政诉讼起数"
            ,"辖区内重大危险源","实际监控"};
    Random random = new Random();

    @Autowired
    TjsEnforcementYearManager tjsEnforcementYearManager;
    @Test
    public void create(){

        List<TjsEnforcementYear> ylist = new ArrayList<>();
        for(int i=2000;i<2018;i++){
            for (String s:metricName){
                TjsEnforcementYear year = new TjsEnforcementYear();
                year.setChemical(random.nextInt(200));
                year.setCoalMine(random.nextInt(200));
                year.setFirecrackers(random.nextInt(200));
                year.setIndustryMetallurgy(random.nextInt(200));
                year.setManufactOther(random.nextInt(200));
                year.setMetallurgy(random.nextInt(200));
                year.setMetricName(s);
                year.setOther(random.nextInt(200));
                year.setTjsOrgan(null);
                year.setTradeOther(random.nextInt(200));
                //year.setUnit(random.nextInt(200));
                ylist.add(year);
            }
            tjsEnforcementYearManager.save(ylist);
        }

    }
}
