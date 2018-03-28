package com.topcom.tjs.dao;

import com.topcom.cms.base.dao.GenericDao;
import com.topcom.tjs.domain.TjsOrgan;

import java.util.List;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
public interface TjsOrganDao extends GenericDao<TjsOrgan,Long> {
    List<TjsOrgan> findByProvince(String province);

    List<TjsOrgan> findByProvinceAndCity(String province, String city);
}
