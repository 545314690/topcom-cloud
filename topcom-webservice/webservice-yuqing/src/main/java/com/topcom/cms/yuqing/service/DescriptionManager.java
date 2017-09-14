package com.topcom.cms.yuqing.service;

import com.topcom.cms.es.vo.AggRequest;
import com.topcom.cms.es.vo.BoolQueryRequest;
import com.topcom.cms.es.vo.KV;

import java.util.List;
import java.util.Map;

/**
 * Created by maxl on 17-6-1.
 */
public interface DescriptionManager {

    Object trendOfOpinion(List<KV> monthNum, List<KV> lastMonthNum);
    Object getProvinceLocality(String[] provinces);
    Object getProvinceLocality(String provinces);
    Object outOFAve(List<KV> monthNum);
    Object monthOpinion(List<KV> monthNum);
    Object monthLyOutline(BoolQueryRequest boolQueryRequest);
    Object specialOutline(BoolQueryRequest boolQueryRequest);

    Object weeklyOutline(BoolQueryRequest boolQueryRequest);
}
