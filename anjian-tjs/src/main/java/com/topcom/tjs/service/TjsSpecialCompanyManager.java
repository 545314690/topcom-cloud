package com.topcom.tjs.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.tjs.domain.TjsSpecialCompany;
import com.topcom.tjs.vo.KVPair;

import java.util.List;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
public interface TjsSpecialCompanyManager extends GenericManager<TjsSpecialCompany, Long> {
    List<TjsSpecialCompany> findByArea(String industryType, String province, String city);

    List<KVPair> countByAreaAndIndustryType(String industryType, String province, String city);

    List<KVPair> countByIndustryTypeAndAreaAndProperty(String industryType, String province, String city,String property);

    List<KVPair> countByAreaAndIndustryTypeAndScale(String industryType, String province, String city);

    List<KVPair> countByAreaAndIndustryTypeAndAdministrative(String industryType, String province, String city);

    List<KVPair> countByAreaAndIndustryTypeAndProductType(String industryType, String province, String city);

    List<KVPair> countByAreaAndIndustryTypeAndLicence(String industryType, String province, String city);

    List<KVPair> countByAreaAndIndustryTypeAndcountByLicenceDate(String industryType, String province, String city);

    List<TjsSpecialCompany> findByCircleArea(Double lat, Double lng, Double radius);
}
