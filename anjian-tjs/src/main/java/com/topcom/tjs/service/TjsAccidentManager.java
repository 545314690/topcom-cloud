package com.topcom.tjs.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.tjs.domain.TjsAccident;
import com.topcom.tjs.vo.KVPair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
public interface TjsAccidentManager extends GenericManager<TjsAccident, Long> {
    List<KVPair> countByAreaAndProperty(String province, String city,String property,String industryType);
    List<KVPair> countByAreaAndGender(String province, String city,String industryType);

    List<KVPair> countByAreaAndType(String province, String city,String industryType);

    List<KVPair> countByAreaAndManageType(String province, String city,String industryType);

    List<KVPair> countByStatusAndInsurance(String province, String city,String industryType);

    List<KVPair> countByProfessionDeath(String province, String city,String industryType);

    List<KVPair> countByEducation(String province, String city,String industryType);

    List<KVPair> countByAttribute(String province, String city,String industryType);

    List<KVPair> countByCFLB(String type, String province, String city, String industryType);

    List<KVPair> countByCFRY(String province, String city, String industryType);

    Page<TjsAccident> findByCompanyIdAndHappenedTimeBetween(Long accidentId, Date startDate, Date endDate, Pageable pageable);
}
