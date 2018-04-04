package com.topcom.tjs.create;

import com.topcom.tjs.domain.TjsAccident;
import com.topcom.tjs.domain.TjsAccidentInvestigation;
import com.topcom.tjs.domain.TjsSpecialCompany;
import com.topcom.tjs.service.TjsAccidentInvestigationManager;
import com.topcom.tjs.service.TjsAccidentManager;
import com.topcom.tjs.service.TjsSpecialCompanyManager;
import com.topcom.tjs.utils.CreateDataUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author maxl
 * @date 2018/3/28 0028
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateAccidentInvestigation {
    Random random = new Random();
    @Autowired
    TjsAccidentManager tjsAccidentManager;
    @Autowired
    TjsAccidentInvestigationManager manager;
    List<String> nameList = CreateDataUtil.getRandomName();
    @Test
    public void create(){
        List<TjsAccident> accList = tjsAccidentManager.findAll();
        List<TjsAccidentInvestigation> investigations = new ArrayList<>();
        for(int i=0;i<accList.size();i++){
            TjsAccident acc = accList.get(i);
            TjsAccidentInvestigation investigation = new TjsAccidentInvestigation();
            investigation.setAccident(acc);
            investigation.setCLDCZRQ(acc.getHappenedTime());
            investigation.setDJCFA(random.nextInt(2));
            investigation.setDJCFB(random.nextInt(5));
            investigation.setDJCFC(random.nextInt(10));
            investigation.setDJCFD(random.nextInt(10));
            investigation.setDJCFE(random.nextInt(10));
            investigation.setDJCFJGA(random.nextInt(2));
            investigation.setDJCFJGB(random.nextInt(5));
            investigation.setDJCFJGC(random.nextInt(10));
            investigation.setDJCFJGD(random.nextInt(10));
            investigation.setDJCFJGE(random.nextInt(10));
            investigation.setDJCFQYA(random.nextInt(2));
            investigation.setDJCFQYB(random.nextInt(5));
            investigation.setDJCFQYC(random.nextInt(10));
            investigation.setDJCFQYD(random.nextInt(10));
            investigation.setDJCFQYE(random.nextInt(10));
            investigation.setFZR(acc.getCompany().getHead());
            investigation.setGBRQ(acc.getHappenedTime());
            investigation.setGPDB(acc.getDeathNumber()>10);
            //挂牌督办 编号
            investigation.setGPDBDWJWH("");
            /**
             * 四分之一的有举报奖励金额   2k到5w
             */
            investigation.setJBJLJE((double) (random.nextInt()%4==0? CreateDataUtil.getGaussianRandom(2000,50000):0));
            investigation.setJYFKE((double) CreateDataUtil.getGaussianRandom(20000,5000000));
            investigation.setPFRQ(new Date(acc.getHappenedTime().getTime()+3*24*3600*1000));
            investigation.setSGMC(acc.getCompanyName()+"事故");
            investigation.setTBR(nameList.get(random.nextInt(nameList.size()-1)));
            investigation.setTBRQ(new Date(acc.getHappenedTime().getTime()+8*3600*1000));
            investigation.setTJSGDCBGRQ(new Date(acc.getHappenedTime().getTime()+2*24*3600*1000));
            investigation.setTJSHR(nameList.get(random.nextInt(nameList.size()-1)));
            investigation.setZJDJCFA(random.nextInt(1));
            investigation.setZJDJCFB(random.nextInt(2));
            investigation.setZJDJCFC(random.nextInt(3));
            investigation.setZJDJCFD(random.nextInt(5));
            investigation.setZJDJCFE(random.nextInt(10));
            investigation.setZJDJCFJGA(random.nextInt(1));
            investigation.setZJDJCFJGB(random.nextInt(2));
            investigation.setZJDJCFJGC(random.nextInt(3));
            investigation.setZJDJCFJGD(random.nextInt(5));
            investigation.setZJDJCFJGE(random.nextInt(10));
            investigation.setZJDJCFQYA(random.nextInt(2));
            investigation.setZJDJCFQYB(random.nextInt(3));
            investigation.setZJDJCFQYC(random.nextInt(8));
            investigation.setZJDJCFQYD(random.nextInt(10));
            investigation.setZJDJCFQYE(random.nextInt(10));
            investigation.setZJXSZRA(random.nextInt(1));
            investigation.setZJXSZRC(random.nextInt(2));
            investigation.setZJXSZRB(random.nextInt(3));
            investigation.setZJXSZRD(random.nextInt(5));
            investigation.setZJXSZRE(random.nextInt(10));
            investigation.setZJXSZRJGA(random.nextInt(1));
            investigation.setZJXSZRJGB(random.nextInt(2));
            investigation.setZJXSZRJGC(random.nextInt(3));
            investigation.setZJXSZRJGD(random.nextInt(5));
            investigation.setZJXSZRJGE(random.nextInt(10));
            investigation.setZJXSZRQYA(random.nextInt(1));
            investigation.setZJXSZRQYB(random.nextInt(2));
            investigation.setZJXSZRQYC(random.nextInt(3));
            investigation.setZJXSZRQYD(random.nextInt(5));
            investigation.setZJXSZRQYE(random.nextInt(10));

            investigations.add(investigation);
            if (i%5000==0){
                manager.save(investigations);
                System.out.println("------------------------------------");
                System.out.println(i);
                System.out.println("------------------------------------");
            }
        }
        manager.save(investigations);
    }

}
