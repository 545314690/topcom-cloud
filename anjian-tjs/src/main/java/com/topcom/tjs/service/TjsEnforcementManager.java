package com.topcom.tjs.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.tjs.domain.TjsEnforcement;
import com.topcom.tjs.vo.KVPair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lism
 * @date 2018/3/26 0026
 */
public interface TjsEnforcementManager extends GenericManager<TjsEnforcement, Long> {
    Page<TjsEnforcement> findByCompanyIdAndZFJCJZSJBetween(Long companyId, Date startDate, Date endDate, Pageable pageable);

    KVPair countByCompanyIdAndDateAndProperty(Long companyId, String startDate, String endDate, String propertyDesc, String property);

    List<KVPair> countByDateAndAreaAndIndustryTypeAndProperty(String startDate, String endDate, String industryType, String province, String city, String companyId, String property);

    /**
     * 执法检查类别：计划执法、专项执法、其他执法
     *
     * @param startDate
     * @param endDate
     * @param industryType
     * @param province
     * @param city
     * @param companyId
     * @return
     */
    List<KVPair> countByDateAndAreaAndIndustryTypeAndCategory(String startDate, String endDate, String industryType, String province, String city, String companyId);

    /**
     * 是否为随机抽取检查单位
     *
     * @param startDate
     * @param endDate
     * @param industryType
     * @param province
     * @param city
     * @param companyId
     * @return
     */
    List<KVPair> countByDateAndAreaAndIndustryTypeAndRandomCheck(String startDate, String endDate, String industryType, String province, String city, String companyId);

    /**
     * 是否整改复查
     *
     * @param startDate
     * @param endDate
     * @param industryType
     * @param province
     * @param city
     * @param companyId
     * @return
     */
    List<KVPair> countByDateAndAreaAndIndustryTypeAndReCheck(String startDate, String endDate, String industryType, String province, String city, String companyId);

    /**
     * 是否含职业卫生执法检查
     *
     * @param startDate
     * @param endDate
     * @param industryType
     * @param province
     * @param city
     * @param companyId
     * @return
     */
    List<KVPair> countByDateAndAreaAndIndustryTypeAndHealth(String startDate, String endDate, String industryType, String province, String city, String companyId);

    /**
     * 是否举报核实执法检查
     *
     * @param startDate
     * @param endDate
     * @param industryType
     * @param province
     * @param city
     * @param companyId
     * @return
     */
    List<KVPair> countByDateAndAreaAndIndustryTypeAndReport(String startDate, String endDate, String industryType, String province, String city, String companyId);

    /**
     * 罚款金额
     *
     * @param startDate
     * @param endDate
     * @param industryType
     * @param province
     * @param city
     * @param companyId
     * @return
     */
    Map<String, Object> sumByDateAndAreaAndIndustryTypeAndFine(String startDate, String endDate, String industryType, String province, String city, String companyId);

    Map<String, Object> sumByDateAndAreaAndIndustryTypeAndProperty(String startDate, String endDate, String industryType, String province, String city, String companyId, List<KVPair> kvPairList);

    /**
     * 执法检查性质分析
     * @param startDate
     * @param endDate
     * @param industryType
     * @param province
     * @param city
     * @param companyId
     * @return
     */
    List<KVPair> countByDateAndAreaAndIndustryTypeAndNature(String startDate, String endDate, String industryType, String province, String city, String companyId);

    /**
     * 执法文书分析
     * @param startDate
     * @param endDate
     * @param industryType
     * @param province
     * @param city
     * @param companyId
     * @return
     */
    Map<String,Object> sumByDateAndAreaAndIndustryTypeAndDocument(String startDate, String endDate, String industryType, String province, String city, String companyId);

    Map<String,List<KVPair>> countByEnforcet(String startDate, String endDate, String industryType, String province, String city);

    Map<String,Object> countByJGZG(String startDate, String endDate, String industryType, String province, String city);

    Map countByZFPZ(String startDate, String endDate, String industryType, String province, String city);
}
