package com.topcom.cms.yuqing.vo.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/1 0001.
 */
public class KV implements Serializable, Comparable<KV> {
    private Object key;
    private Object value;

    public KV() {
    }

    public KV(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public int compareTo(KV o) {
        if (o != null && o.value instanceof Comparable) {
            return ((Comparable) this.value).compareTo((Comparable) o.value);
        }
        return 0;
    }

    public static Map kvList2Map(List<KV> kvList){
        Map result = new HashMap();
        if (kvList!=null&&kvList.size()>0){
            for (KV kv:kvList){
                result.put(kv.key,kv.getValue());
            }
        }
        return result;
    }

    public static List<KV> map2KvList(Map map){
        List<KV> kvList = new ArrayList<>();
        for (Object s:map.keySet()){
            kvList.add(new KV(s,map.get(s)));
        }
        return kvList;
    }

    @Override
    public String toString() {
        return "KV{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
