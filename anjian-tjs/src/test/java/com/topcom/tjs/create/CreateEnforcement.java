package com.topcom.tjs.create;

import com.topcom.cms.common.page.DateParam;
import com.topcom.tjs.domain.TjsEnforcement;
import com.topcom.tjs.domain.TjsSpecialCompany;
import com.topcom.tjs.service.TjsEnforcementManager;
import com.topcom.tjs.service.TjsSpecialCompanyManager;
import com.topcom.tjs.utils.CreateDataUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.text.DecimalFormat;
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
public class CreateEnforcement {
    @Autowired
    TjsSpecialCompanyManager tjsSpecialCompanyManager;
    @Autowired
    TjsEnforcementManager manager;
    List<String> nameList = CreateDataUtil.getRandomName();

    String[] ZFJCLB = new String[]{ "计划执法","专项执法","其他执法"};
    Random random = new Random();
    @Test
    public void create(){
        DecimalFormat df = new DecimalFormat("######0");
        List<TjsSpecialCompany> companyList = tjsSpecialCompanyManager.findAll();
        List<TjsEnforcement> enforcementList = new ArrayList<>();
        for (TjsSpecialCompany company:companyList){
            try {

            TjsEnforcement enforcement = new TjsEnforcement();
            enforcement.setBJCDWMC(company.getCompanyName());
            //查处项 全部 0-20
            enforcement.setCCAQSCWFWGXWX(CreateDataUtil.getGaussianRandomBigLeft(0,20));
            //查处重大 0-全部
            enforcement.setCCZDSGYHX(CreateDataUtil.getGaussianRandomBigLeft(0,enforcement.getCCAQSCWFWGXWX()));
            //查处项一般 全部-重大
            enforcement.setCCYBSGYHX(enforcement.getCCAQSCWFWGXWX()-enforcement.getCCZDSGYHX());
            enforcement.setCompany(company);
            //大类
            enforcement.setDL("");
            //处罚 重大隐患>2处罚
            enforcement.setDSCJYDWXZCF(enforcement.getCCZDSGYHX()>2);
            //对生产经营单位行政处罚
            enforcement.setDSCJYDWZYFZRXZCF(enforcement.getCCZDSGYHX()>2);
            //罚款  如果有处罚  罚款为2000到重大隐患*3000的高斯分布随机数
            enforcement.setFKE((double) (enforcement.getDSCJYDWXZCF()?CreateDataUtil.getGaussianRandom(2000,enforcement.getCCZDSGYHX()*3000):0));
            //负责人
            enforcement.setFZR(nameList.get(random.nextInt(nameList.size()-1)));
            //管理分类
            enforcement.setGLFL(company.getGLFL());
            //挂牌督办项  0-重大
            enforcement.setGPDBX(enforcement.getCCZDSGYHX());
            enforcement.setJFJCLB(ZFJCLB[random.nextInt(2)]);
            enforcement.setJJCF(enforcement.getCCZDSGYHX()>2);
            enforcement.setLASPB(enforcement.getCCZDSGYHX()>2);
            //门类
            enforcement.setML("");
            //其他文书
            enforcement.setQTWS("");
            enforcement.setQYFZR(nameList.get(random.nextInt(nameList.size()-1)));
            enforcement.setQYLB(company.getCompanyType());
            enforcement.setQYZCDZP(company.getProvince());
            enforcement.setQZCZJDS(enforcement.getCCZDSGYHX()>4);
            enforcement.setQYZCDZS(company.getCity());
            enforcement.setQYZCDZX(company.getCounty());
            enforcement.setSCJJDZP(company.getProvince());
            enforcement.setSCJJDZS(company.getCity());
            enforcement.setSCJJDZX(company.getCounty());
            enforcement.setSFHZYWSZFJC(random.nextBoolean());
            enforcement.setSFWSJCQJCDW(random.nextBoolean());
            enforcement.setSFJBHSZFJC(random.nextBoolean());
            enforcement.setSFZGFC(random.nextBoolean());
            enforcement.setSHXYDM(company.getSCC());
            enforcement.setSJGB(random.nextBoolean());
            //实际缴罚款
            enforcement.setSJSJFK((double) (enforcement.getCCZDSGYHX()>2?CreateDataUtil.getGaussianRandom(2000,Integer.parseInt(df.format(enforcement.getFKE()))):0));
            //中类
            enforcement.setSL("");
            enforcement.setSSHY(company.getIndustryType());
            enforcement.setTBR(nameList.get(random.nextInt(nameList.size()-1)));
            enforcement.setTBRQ(CreateDataUtil.getRandomDate(new DateParam("2016-01-01","2018-03-31")));
            enforcement.setTQGB(random.nextBoolean());
            enforcement.setXCCLCSJDS(random.nextBoolean());
            enforcement.setXCJCJL(random.nextBoolean());
            //小类
            enforcement.setXL("");
            enforcement.setXWBL(random.nextBoolean());
            enforcement.setXZCFJDSDW(CreateDataUtil.getGaussianRandom(0,enforcement.getCCAQSCWFWGXWX()));
            enforcement.setXZCFJDSGR(CreateDataUtil.getGaussianRandom(0,enforcement.getXZCFJDSDW()));
            enforcement.setXZDCCFJDSDW(enforcement.getXZCFJDSDW());
            enforcement.setYZGAQSCWFWGXW(CreateDataUtil.getGaussianRandom(0,enforcement.getXZCFJDSDW()));
            enforcement.setYZGYBSGYH(CreateDataUtil.getGaussianRandom(0,enforcement.getCCYBSGYHX()));
            enforcement.setYZGZDSGYH(CreateDataUtil.getGaussianRandom(0,enforcement.getCCZDSGYHX()));
            //截至时间为填报时间加一个月
            enforcement.setZFJCJZSJ(new Date(enforcement.getTBRQ().getTime()+30*24*3600*1000));
            //起始时间为填报时间
            enforcement.setZFJCKSSS(enforcement.getTBRQ());
            enforcement.setZFJCXZ(random.nextBoolean()?"执法监察":"工作性检测");
            enforcement.setZFWF(CreateDataUtil.getGaussianRandom(0,enforcement.getCCYBSGYHX()));
            enforcement.setZGFCYJS(random.nextBoolean());
            //5个重大隐患  整顿
            enforcement.setZLTCZD(enforcement.getCCZDSGYHX()>4);
            enforcement.setZLXQZGZLS(enforcement.getCCZDSGYHX()>4);
            enforcementList.add(enforcement);

            }
            catch (Exception e){
                e.printStackTrace();
                continue;
            }
        }

        manager.save(enforcementList);
    }


}
