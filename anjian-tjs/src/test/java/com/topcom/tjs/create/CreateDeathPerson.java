package com.topcom.tjs.create;

import com.topcom.tjs.domain.TjsAccident;
import com.topcom.tjs.domain.TjsDeathPerson;
import com.topcom.tjs.service.TjsAccidentManager;
import com.topcom.tjs.service.TjsDeathPersonManager;
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
 * @author maxl
 * @date 2018/3/27 0027
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateDeathPerson {
    Random random = new Random();

    @Autowired
    TjsAccidentManager tjsAccidentManager;
    List<String> nameList = CreateDataUtil.getRandomName();
    @Autowired
    TjsDeathPersonManager tjsDeathPersonManager;
    String[] education = new String[]{"小学","小学","初中","初中","初中","高中","高中","学士","硕士"};
    String[] gender = new String[]{"男","男","男","女","女"};
    @Test
    public void create(){
        List<TjsAccident> accList = tjsAccidentManager.findAll();
        List<TjsDeathPerson> personList = new ArrayList<>();
        int count =0;
        for (int i=0;i<accList.size();i++){
            TjsAccident acc = accList.get(i);
            Integer deathNumber = acc.getDeathNumber();  //死亡人数
            Integer terribleNumber = acc.getTerribleNumber(); //重伤人数
            if (deathNumber!=null&&deathNumber>0){
                for (int j=0;j<deathNumber;j++){
                    TjsDeathPerson person = new TjsDeathPerson();
                    person.setStatus("死亡");
                    person = initDeathNuber(acc,person);
                    personList.add(person);
                }
            }
            if (terribleNumber!=null&&terribleNumber>0){
                for (int j=0;j<terribleNumber;j++){
                    TjsDeathPerson person = new TjsDeathPerson();
                    person.setStatus("重伤");
                    person = initDeathNuber(acc,person);
                    personList.add(person);
                }
            }
            if (personList.size()%5000==0){
                count=count+personList.size();
                tjsDeathPersonManager.save(personList);
                personList.clear();
                System.out.println("-----------------------------------------------------------");
                System.out.println(i);
                System.out.println("保存："+count+"条");
                System.out.println("-----------------------------------------------------------");
            }
        }
        tjsDeathPersonManager.save(personList);
    }


    public TjsDeathPerson initDeathNuber(TjsAccident acc,TjsDeathPerson person){
        person.setAccident(acc);
        person.setAge(CreateDataUtil.getGaussianRandom(25,60));
        person.setCardId("未知");
        person.setDeathDate(acc.getHaddenedTime());
        person.setEducation(education[CreateDataUtil.getGaussianRandomBigLeft(0,education.length-1)]);
        person.setGender(gender[CreateDataUtil.getGaussianRandomBigLeft(0,gender.length-1)]);
        person.setIndustrialInsurance(random.nextBoolean());
        person.setName(nameList.get(random.nextInt(nameList.size()-1)));
        //职业
        person.setProfession("");
        person.setProfessionDeath(random.nextBoolean());
        return person;
    }
}
