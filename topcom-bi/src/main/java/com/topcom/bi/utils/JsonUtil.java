package com.topcom.bi.utils;

import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

/**
 * @author maxl
 * @date 2018/3/15 0015
 */
public class JsonUtil {

    public static JsonConfig getNoNullConfig(){
        JsonConfig config = new JsonConfig();
        config.setJsonPropertyFilter(new PropertyFilter() {
            @Override
            public boolean apply(Object o, String s, Object o1) {
                return o1==null||o1.equals(JSONNull.getInstance());
            }
        });
        return config;
    }

    public static JSONObject toJsonNoNull(Object object){
        JsonConfig config = getNoNullConfig();
        return JSONObject.fromObject(object,config);
    }
}
