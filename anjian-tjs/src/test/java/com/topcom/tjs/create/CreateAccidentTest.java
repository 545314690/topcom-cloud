package com.topcom.tjs.create;

import com.mongodb.DBObject;
import com.topcom.cms.utils.MongoDBUtil;
import com.topcom.tjs.domain.TjsAccident;
import com.topcom.tjs.domain.TjsSpecialCompany;
import com.topcom.tjs.service.TjsAccidentManager;
import com.topcom.tjs.service.TjsSpecialCompanyManager;
import com.topcom.tjs.utils.CreateDataUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author maxl
 * @date 2018/3/27 0027
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateAccidentTest {
    Random random = new Random();
    @Autowired
    TjsAccidentManager accidentManager;

    @Autowired
    TjsSpecialCompanyManager tjsSpecialCompanyManager;


    String[] factors = new String[]{"火灾","特种设备","危险化学品","民爆"};
    @Test
    public void createAndSave(){

        List<TjsSpecialCompany> all = tjsSpecialCompanyManager.findAll();
        List<DBObject> dbObjectList = MongoDBUtil.selectAll("acc");
        List<TjsAccident> accidentList = new ArrayList<>();
        for (int i =30000;i<dbObjectList.size();i++){
            try {
            TjsSpecialCompany company = all.get(random.nextInt(all.size() - 1));
            DBObject object = dbObjectList.get(i);
            TjsAccident tjsAccident = new TjsAccident();
            tjsAccident.setAddress(company.getAddress());
            tjsAccident.setCity(company.getCity());
            tjsAccident.setCompany(company);
            tjsAccident.setCompanyAttribute(company.getCompanyAttribute());
            tjsAccident.setCompanyIndustry(company.getIndustryType());
            tjsAccident.setCompanyName(company.getCompanyName());
            //企业的详细情况
            tjsAccident.setCompanyProfile("");
            tjsAccident.setCompanyScale(company.getCompanyType());
            tjsAccident.setCompanyType(company.getLogoType());
            tjsAccident.setCounty(company.getCounty());
            //死亡人数  0-100 左边重的正态分布
            tjsAccident.setDeathNumber((Integer) object.get("deathnumber"));
            tjsAccident.setDescription("");
            tjsAccident.setFactors(factors[random.nextInt(factors.length-1)]);
            tjsAccident.setHappenedTime((Date) object.get("adate"));
            tjsAccident.setInjuredNumber(CreateDataUtil.getGaussianRandomBigLeft(0,100));
            tjsAccident.setLat(company.getLat());
            tjsAccident.setLng(company.getLng());
            //经济损失单位万
            tjsAccident.setLoss((double) CreateDataUtil.getGaussianRandom(10,9000));
            tjsAccident.setManageType(company.getIndustryType());
            tjsAccident.setProfile("");
            tjsAccident.setReport(random.nextBoolean());
            tjsAccident.setProvince(company.getProvince());
            tjsAccident.setSCC(company.getSCC());
            tjsAccident.setTerribleNumber(CreateDataUtil.getGaussianRandomBigLeft(0,tjsAccident.getInjuredNumber()));
            tjsAccident.setType(object.get("atype").toString());
            accidentList.add(tjsAccident);
            }
            catch (Exception e){
                e.printStackTrace();
                continue;
            }
            if (accidentList.size()%5000==0){
                accidentManager.save(accidentList);
                accidentList.clear();
                System.out.println(i);
            }
        }
        accidentManager.save(accidentList);
    }
}
