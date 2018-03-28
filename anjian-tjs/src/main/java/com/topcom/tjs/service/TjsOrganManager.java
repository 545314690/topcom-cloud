package com.topcom.tjs.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.tjs.domain.TjsOrgan;
import com.topcom.tjs.vo.KVPair;

import java.util.List;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
public interface TjsOrganManager extends GenericManager<TjsOrgan, Long> {
    List<KVPair> countByArea(String province, String city);

    List<TjsOrgan> findByProvince(String province);

    List<TjsOrgan> findByProvinceAndCity(String province, String city);
}
