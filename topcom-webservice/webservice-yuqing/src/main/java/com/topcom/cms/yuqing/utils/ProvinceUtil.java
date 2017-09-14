package com.topcom.cms.yuqing.utils;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maxl on 17-6-2.
 */
public class ProvinceUtil {



    static Map<String,String> province2Locality = new HashMap<String,String>(){
        {
            put("山东","华东");
            put("江苏","华东");
            put("安徽","华东");
            put("浙江","华东");
            put("福建","华东");
            put("上海","华东");
            put("广东","华南");
            put("广西","华南");
            put("海南","华南");
            put("湖北","华中");
            put("湖南","华中");
            put("河南","华中");
            put("江西","华中");
            put("北京","华北");
            put("天津","华北");
            put("河北","华北");
            put("山西","华北");
            put("内蒙古","华北");
            put("宁夏","西北");
            put("新疆","西北");
            put("青海","西北");
            put("陕西","西北");
            put("甘肃","西北");
            put("四川","西南");
            put("云南","西南");
            put("贵州","西南");
            put("西藏","西南");
            put("重庆","西南");
            put("辽宁","东北");
            put("吉林","东北");
            put("黑龙江","东北");
            put("台湾","港澳台");
            put("香港","港澳台");
            put("澳门","港澳台");
        }
    };



    public static String getLocality(String province){
        if (StringUtils.isEmpty(province)){
            return null;
        }
        province = province.replaceAll("省","").replaceAll("自治区","");
        if (province2Locality.containsKey(province)){
            return province2Locality.get(province);
        }else return null;
    }
}
