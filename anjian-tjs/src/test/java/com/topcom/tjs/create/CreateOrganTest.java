package com.topcom.tjs.create;

import com.topcom.tjs.domain.TjsOrgan;
import com.topcom.tjs.service.TjsOrganManager;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author maxl
 * @date 2018/3/27 0027
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class CreateOrganTest {

    @Autowired
    TjsOrganManager tjsOrganManager;

    @Test
    public void create() throws IOException {
        List<TjsOrgan> result = new ArrayList<>();
        File file = new File("D:\\data\\china-area-data\\data.json");
        String content= FileUtils.readFileToString(file,"UTF-8");
        JSONObject json = JSONObject.fromObject(content);//获取省市json
        List<String> organList = new ArrayList<>();
        JSONObject province = json.getJSONObject("86");
        for (Object s:province.keySet()){
            String provinceName = province.getString(s.toString());
            organList.add(provinceName+"监察局");

            TjsOrgan proviceOrgan = new TjsOrgan();
            proviceOrgan.setAddr(provinceName);
            proviceOrgan.setProvince(provinceName);
            proviceOrgan.setName(provinceName+"监察局");
            proviceOrgan = initOrgan(proviceOrgan);
            proviceOrgan.setType("省级");
            result.add(proviceOrgan);

            JSONObject city = json.getJSONObject(s.toString());
            for (Object c:city.keySet()){
                organList.add(provinceName+city.getString(c.toString())+"监察部门");
                TjsOrgan organ = new TjsOrgan();
                organ.setAddr(provinceName+city.getString(c.toString()));
                organ.setProvince(provinceName);
                organ.setCity(city.getString(c.toString()));
                organ.setName(provinceName+city.getString(c.toString())+"监察部门");
                organ = initOrgan(organ);
                organ.setType("地市级");
                result.add(organ);
            }
        }
        System.out.println(organList.size());
        //tjsOrganManager.save(result);
    }

    public TjsOrgan initOrgan(TjsOrgan organ){
        return organ;
    }
}
