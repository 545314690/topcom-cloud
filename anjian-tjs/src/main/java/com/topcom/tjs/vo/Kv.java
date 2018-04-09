package com.topcom.tjs.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Kv implements Serializable {
   private Object name;
   private Object value;


    public Kv() {
    }

    public Kv(Object name, Object value) {
        this.name = name;
        this.value = value;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }


    public static List<Kv> map2Kv(Map map){
        List<Kv> kvList =new ArrayList<>();
        for (Object o:map.keySet()){
            kvList.add(new Kv(o,map.get(o)));
        }
        return kvList;
    }
}
