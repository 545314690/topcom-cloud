package com.topcom.cms.yuqing.vo.request;

import java.util.List;

/**
 * Created by maxl on 17-6-2.
 */
public class OpinionAndTime {
    private List<KV> monthNum;
    private List<KV> lastMonthNum;

    public List<KV> getMonthNum() {
        return monthNum;
    }

    public void setMonthNum(List<KV> monthNum) {
        this.monthNum = monthNum;
    }

    public List<KV> getLastMonthNum() {
        return lastMonthNum;
    }

    public void setLastMonthNum(List<KV> lastMonthNum) {
        this.lastMonthNum = lastMonthNum;
    }

//    public Map<String,String> getMonthNmMap(){
//        Map<String,String> map = new HashMap<>();
////        for (Object s:this.monthNum.keySet()){
////            map.put(s.toString(),monthNum.get(s).toString());
////        }
//        return map;
//    }
//    public Map<String,String> getLastMonthNmMap(){
//        Map<String,String> map = new HashMap<>();
////        for (Object s:this.lastMonthNum.keySet()){
////            map.put(s.toString(),lastMonthNum.get(s).toString());
////        }
//        return map;
//    }
}
