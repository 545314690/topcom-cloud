package com.topcom.tjs.create;

import com.topcom.tjs.domain.TjsOrgan;
import com.topcom.tjs.domain.TjsOrganPerson;
import com.topcom.tjs.service.TjsOrganManager;
import com.topcom.tjs.service.TjsOrganPersonManager;
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
 * @date 2018/3/28 0028
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateOrganPerson {
    Random random = new Random();
    @Autowired
    TjsOrganManager tjsOrganManager;

    @Autowired
    TjsOrganPersonManager manager;

    String[] level = new String[]{"厅局级","县处级","乡科级","其他"};

    List<String> nameList = CreateDataUtil.getRandomName();
    List<TjsOrganPerson> personList = new ArrayList<>();
    @Test
    public void  create(){
        List<TjsOrgan> organList = tjsOrganManager.findAll();
        for (TjsOrgan organ:organList){
            int sum = CreateDataUtil.getGaussianRandom(5,20);
            for (int i=0;i<sum;i++){
                TjsOrganPerson person = new TjsOrganPerson();
                person.setAge(CreateDataUtil.getGaussianRandom(25,55));
                person.setGender(random.nextInt()%2==0?"男":"女");
                person.setLevel(level[random.nextInt(level.length-1)]);
                person.setName(nameList.get(random.nextInt(nameList.size()-1)));
                //执法编号
                person.setNumber("");
                //备注
                person.setRemarks("无");
                person.setOrgan(organ);
                personList.add(person);
            }
        }
        manager.save(personList);
    }

}
