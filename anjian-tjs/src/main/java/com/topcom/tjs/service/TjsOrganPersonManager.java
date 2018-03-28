package com.topcom.tjs.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.tjs.domain.TjsOrganPerson;
import com.topcom.tjs.vo.KVPair;

import java.util.List;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
public interface TjsOrganPersonManager extends GenericManager<TjsOrganPerson, Long> {
    List<KVPair> countByAreaAndProperty(String province, String city,String property);

    List<KVPair> countByAreaAndLevel(String province, String city);

    List<KVPair> countByAreaAndGender(String province, String city);

    List<KVPair> countByAreaAndEducation(String province, String city);

    List<KVPair> countByAreaAndAge(String province, String city);

    List<KVPair> countByAreaAndCredentials(String province, String city);
}
