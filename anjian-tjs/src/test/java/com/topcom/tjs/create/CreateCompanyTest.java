package com.topcom.tjs.create;

import com.mongodb.DBObject;
import com.topcom.cms.common.page.DateParam;
import com.topcom.cms.utils.MongoDBUtil;
import com.topcom.tjs.domain.TjsSpecialCompany;
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
public class CreateCompanyTest {

    @Autowired
    TjsSpecialCompanyManager tjsSpecialCompanyManager;
    String[] type= new String[]{"大型" , "中型" , "小型" , "微型"};
    String[] companyAttribute = new String[]{"央企", "省属","市地属", "区县属","其他"};
    String[] industryType = new String[]{"煤矿","金属非金属矿山","化工","烟花爆竹","工贸行业"};
    String[] session = new String[]{"有","延期换证","无"};
    String[] logoType = new String[]{"国有企业" ,"集体企业", "股份合作企业" ,"联营企业", "有限责任公司" ,"股份有限公司",
            "私营企业" , "其他内资企业", "港、澳、台商投资企业" ,  "中外合资合作企业" ,"外商投资企业 "};
    String[] productType =new String[]{"生产(制造)" , "批发经营","零售经营" , "储存",  "使用"  ,"其他"};
    Random random = new Random();
    @Test
    public void createCompany(){
        List<TjsSpecialCompany> companyList = new ArrayList<>();
        List<DBObject> dbObjectList = MongoDBUtil.selectAll("mine_base");
        List<String> fillList = CreateDataUtil.getRandomName(dbObjectList.size()/20);
        List<String> nameList = CreateDataUtil.getRandomName(dbObjectList.size()+2);
        for (int i=0;i<dbObjectList.size();i++){
            try {
                DBObject object = dbObjectList.get(i);
                TjsSpecialCompany company = new TjsSpecialCompany();
                company.setAddress(object.get("cityinfo").toString());
                //注销 与 不注销 10：1
                company.setCancellation(random.nextInt(100) / 10 == 0 ? true : false);
                company.setCity(object.get("市").toString());
                //行政隶属关系  ○央企  ○省属  ○市地属  ○区县属   ○其他
                company.setCompanyAttribute(companyAttribute[random.nextInt(companyAttribute.length - 1)]);
                company.setCompanyName(object.get("企业名称").toString());
                company.setCompanyType(type[random.nextInt(type.length - 1)]);
                company.setCounty(object.get("县").toString());
                //表的创建时间  2016-01-01 2018-03-31
                company.setCreateTableDate(CreateDataUtil.getRandomDate(new DateParam("2016-01-01", "2018-03-31")));
                //填表人 平均20个公司对应一个填表人
                company.setFillPerson(fillList.get(random.nextInt(fillList.size() - 1)));
                company.setHazardousChemicals(random.nextBoolean());
                //负责人
                company.setHead(nameList.get(i));
                //行业分类代码 与所属行业对应
                company.setIndustryNumber(random.nextInt(5));
                company.setIndustryType(industryType[company.getIndustryNumber()]);
                company.setLat(Double.valueOf(object.get("lat").toString()));
                company.setLawPlan(random.nextBoolean());
                company.setLicence(session[random.nextInt(2)]);
                company.setLicenceStartDate(CreateDataUtil.getRandomDate(new DateParam("2016-01-01", "2017-12-12")));
                //结束时间为开始时间加上6个月
                company.setLicenceEndDate(new Date((long) (company.getLicenceStartDate().getTime() + 6 * 30.5 * 24 * 60 * 60)));
                company.setLng(Double.valueOf(object.get("lng").toString()));
                company.setLogoType(logoType[random.nextInt(logoType.length - 1)]);
                company.setNumber(random.nextLong());
                //发证机关
                company.setOrganName("");

                //从业人员数量
                company.setPersonNumber(CreateDataUtil.getGaussianRandom(10, 500));
                company.setProductType(productType[random.nextInt(productType.length - 1)]);
                company.setProvince(object.get("省").toString());
                company.setScale(random.nextBoolean());
                company.setSCC("xxxxxxxxxxxxxxxxxxx");
                company.setSpecial(random.nextBoolean());
                companyList.add(company);
            }
            catch (Exception e){
                e.printStackTrace();
                continue;
            }
        }
        tjsSpecialCompanyManager.save(companyList);
    }
}
