package com.topcom.cms.yuqing.service.impl;

import com.mysql.jdbc.log.LogUtils;
import com.topcom.cms.common.page.DateParam;
import com.topcom.cms.common.page.PageRequest;
import com.topcom.cms.common.utils.DateUtil;
import com.topcom.cms.common.utils.LogUtil;
import com.topcom.cms.es.service.AllESDataService;
import com.topcom.cms.es.service.CommentsService;
import com.topcom.cms.es.vo.AggRequest;
import com.topcom.cms.es.vo.BoolQueryRequest;
import com.topcom.cms.es.vo.KV;
import com.topcom.cms.es.vo.Keyword;
import com.topcom.cms.mongo.service.AccidentService;
import com.topcom.cms.yuqing.service.DescriptionManager;
import com.topcom.cms.yuqing.utils.ProvinceUtil;
import jdk.nashorn.internal.parser.DateParser;
import net.sf.json.JSONObject;
import org.apache.lucene.util.MathUtil;
import org.elasticsearch.common.math.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by maxl on 17-6-1.
 */
@Service(value = "descriptionManager")
@Transactional
public class DescriptionManagerImpl implements DescriptionManager {

    @Autowired
    AccidentService accidentService;


    @Autowired
    CommentsService commentsService;


    @Autowired
    AllESDataService allESDataService;

    //月报舆情趋势
    @Override
    public Object trendOfOpinion(List<KV> monthNum,List<KV> lastMonthNum) {

        List<Double> doubles1 = new ArrayList<>();
        List<Double>  doubles2 = new ArrayList<>();
        for (int i = 0;i<monthNum.size();i++){
            doubles1.add(Double.valueOf(monthNum.get(i).getValue().toString()));
        }
        for (int i=0;i<lastMonthNum.size();i++){
            doubles2.add(Double.valueOf(lastMonthNum.get(i).getValue().toString()));
        }
        if (variance(doubles1)>variance(doubles2)){
            return true;
        }
        return false;
    }



    @Override
    public Object getProvinceLocality(String[] provinces) {
        List<KV> result = new ArrayList<>();
        for (String s:provinces){
            String locality= ProvinceUtil.getLocality(s);
            result=keyInListKV(result,s,locality);
        }
        return result;
    }

    @Override
    public Object getProvinceLocality(String provinces) {
        String[] provincess = provinces.split(",");
        return getProvinceLocality(provincess);
    }
    private List<KV> keyInListKV(List<KV> kvList,String s,String locality) {
        if (StringUtils.isEmpty(locality)){
            return kvList;
        }
        if (kvList.size()<1){
            kvList.add(new KV(locality,s));
            return kvList;
        }
        Boolean haveKey = false;
        for (int i=0;i<kvList.size();i++){
            KV kv = kvList.get(i);
            if (locality.equals(kv.getKey())){
                kv.setValue(kv.getValue().toString()+","+s);
               // kvList.add(i,kv);
                haveKey=true;
                break;
            }
        }
        if (!haveKey){
            kvList.add(new KV(locality,s));
        }
        return kvList;
    }

    @Override
    public Object outOFAve(List<KV> monthNum) {
        Map<String,Object> result = new HashMap<>();
        List<Double> doubles1 = new ArrayList<>();
        for (int i= 0;i<monthNum.size();i++){
            doubles1.add(Double.valueOf(monthNum.get(i).getValue().toString()));
        }
        double average = average(doubles1);
        List pre30List = new ArrayList();
        List pre50List = new ArrayList();
        for (int i= 0;i<monthNum.size();i++){
            double num = Double.valueOf(monthNum.get(i).getValue().toString());
            if (num>(average*1.5)){
                pre50List.add(monthNum.get(i).getKey());

            }else if (num>(average*1.3)){
                pre30List.add(monthNum.get(i).getKey());
            }
        }

        if ((pre30List.size()+pre50List.size())>0){
            result.put("pre50",pre50List);
            result.put("pre30",pre30List);
            return result;
        }
        return null;
    }

    @Override
    public Object monthOpinion(List<KV> monthNum) {
        if (monthNum.size()<28){
            LogUtil.logger.info("当月数据长度不够");
            return null;
        }
        List<Double> doubles1 = new ArrayList<>();
        List<Double>  doubles2 = new ArrayList<>();
        List<Double>  doubles3 = new ArrayList<>();
        for (int i =0;i<monthNum.size();i++){
            if (i<10){

            }
        }
        return null;
    }


    private double average(List<Double> doubles){
        double sum = 0;
        for (int i=0;i<doubles.size();i++){
            sum = sum+doubles.get(i);
        }
        return sum/doubles.size();
    }
    private double variance(List<Double> doubles){
        double average=average(doubles);
        double variance=0;
        if (doubles.size()<1){
            return 0;
        }
        for (int i = 0;i<doubles.size();i++){
            variance = variance+((doubles.get(i)-average)*(doubles.get(i)-average));
        }
        return variance;
    }




    @Override
    public Object monthLyOutline(BoolQueryRequest boolQueryRequest) {
        boolQueryRequest.setPage(new PageRequest(1,1));
        JSONObject jsonObject = new JSONObject();
        Page page = allESDataService.findByMustShouldDateInType(boolQueryRequest);
        jsonObject.put("monthCount",page.getTotalElements());
        jsonObject.put("mom",getmom(boolQueryRequest,"month"));
        jsonObject.put("maxSite",getMaxType(boolQueryRequest,"site"));
        jsonObject.put("maxType",getMaxType(boolQueryRequest,"type"));
        jsonObject.put("compare",compareWithLastMonthEverySite(boolQueryRequest,"month"));
        jsonObject.put("label",getMaxType(boolQueryRequest,"nlp.sentiment.label"));
        return jsonObject;
    }

    @Override
    public Object specialOutline(BoolQueryRequest boolQueryRequest) {
        boolQueryRequest.setPage(new PageRequest(1,1));
        JSONObject jsonObject = new JSONObject();
        Page page = allESDataService.findByMustShouldDateInType(boolQueryRequest);
        jsonObject.put("monthCount",page.getTotalElements());
        jsonObject.put("maxType",getMaxType(boolQueryRequest,"type"));
        jsonObject.put("label",getMaxType(boolQueryRequest,"nlp.sentiment.label"));
        boolQueryRequest.setType("news");
        jsonObject.put("maxSite",getMaxType(boolQueryRequest,"site"));
        jsonObject.put("maxTitle",getMaxType(boolQueryRequest,"title.raw"));
        return jsonObject;
    }

    @Override
    public Object weeklyOutline(BoolQueryRequest boolQueryRequest) {
        boolQueryRequest.setPage(new PageRequest(1,1));
        JSONObject jsonObject = new JSONObject();
        Page page = allESDataService.findByMustShouldDateInType(boolQueryRequest);
        jsonObject.put("weekCount",page.getTotalElements());
        jsonObject.put("wow",getmom(boolQueryRequest,"week"));
        jsonObject.put("maxSite",getMaxType(boolQueryRequest,"site"));
        jsonObject.put("maxType",getMaxType(boolQueryRequest,"type"));
        jsonObject.put("compare",compareWithLastMonthEverySite(boolQueryRequest,"week"));
        jsonObject.put("label",getMaxType(boolQueryRequest,"nlp.sentiment.label"));
        return jsonObject;
    }

    private Object compareWithLastMonthEverySite(BoolQueryRequest boolQueryRequest,String type) {
        DateParam lastDateparam = lastDateparam(boolQueryRequest.getDate(),type);
        Map thisMonthGroupByType = KV.kvList2Map(getMaxType(boolQueryRequest,"type"));
        boolQueryRequest.setDate(lastDateparam);
        Map lastMonthGroupByType = KV.kvList2Map(getMaxType(boolQueryRequest,"type"));
        Map<String,Double> routMap = new HashMap<>();
        for (Object object:thisMonthGroupByType.keySet()){
            if (lastMonthGroupByType.containsKey(object)){
                long thisMonthcount = Long.parseLong(thisMonthGroupByType.get(object).toString());
                long lastMonthcount = Long.parseLong(lastMonthGroupByType.get(object).toString());
                double rout = (double) (thisMonthcount-lastMonthcount)/lastMonthcount;
                routMap.put(object.toString(),rout);
            }
        }

        return paixu(routMap);
    }

    private List<KV> getMaxType(BoolQueryRequest boolQueryRequest,String groupName) {
        Keyword keyword = boolQueryRequest.getKeyword();
        DateParam dateParam = boolQueryRequest.getDate();
        String articleType = "";
        String[] type = boolQueryRequest.getType();
        if (type!=null&&type.length>0){
            articleType = type[0];
            for (int i=1;i<type.length;i++){
                articleType = articleType+"@"+type[i];
            }
        }else {
            articleType = "article";
        }
        List<KV> groupByType = allESDataService.filterAndGroupBy(keyword.getMustWord(),keyword.getShouldWord(),
                keyword.getMustNotWord(),dateParam.getStartDate(),dateParam.getEndDate(),boolQueryRequest.getFiled(),
                groupName,articleType,null,20);
        if (groupByType==null||groupByType.size()==0){
            return null;
        }
        return groupByType;
    }

    private Object getmom(BoolQueryRequest boolQueryRequest,String type) {
        DateParam lastDateparam = lastDateparam(boolQueryRequest.getDate(),type);
        Page thisMonth = allESDataService.findByMustShouldDateInType(boolQueryRequest);
        BoolQueryRequest boolQueryRequestLast = new BoolQueryRequest(lastDateparam,boolQueryRequest.getKeyword());
        boolQueryRequestLast.setType(boolQueryRequest.getType());
        boolQueryRequestLast.setSearchKv(boolQueryRequest.getSearchKv());
        boolQueryRequestLast.setPage(boolQueryRequest.getPage());
        boolQueryRequestLast.setFiled(boolQueryRequest.getFiled());
        Page lastMonth = allESDataService.findByMustShouldDateInType(boolQueryRequestLast);
        return (double)(thisMonth.getTotalElements()-lastMonth.getTotalElements())/lastMonth.getTotalElements();
    }

    private DateParam lastDateparam(DateParam date,String type) {
        DateParam lastDateParam = new DateParam();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date.startDate());
        lastDateParam.setEndDate(date.getStartDate());
        if (type.equals("week")){
            calendar.add(Calendar.WEEK_OF_MONTH,-1);
            SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
            lastDateParam.setStartDate(dateFormater.format(calendar.getTime()));
        }else if (type.equals("month")){
            calendar.add(Calendar.MONTH,-1);
            SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM");
            lastDateParam.setStartDate(dateFormater.format(calendar.getTime()));
        }

        return lastDateParam;
    }

    private List<Map.Entry<String, Double>> paixu(Map<String, Double> coats){
        List<Map.Entry<String, Double>> mappingList = null;
        mappingList = new ArrayList<Map.Entry<String, Double>>(coats.entrySet());
        Collections.sort(mappingList, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> mapping1, Map.Entry<String, Double> mapping2) {
                return -mapping1.getValue().compareTo(mapping2.getValue());
            }
        });
        return mappingList;
    }
}
