package com.topcom.tjs.dao;

import com.topcom.cms.base.dao.GenericDao;
import com.topcom.tjs.domain.TjsOrganPerson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author maxl
 * @date 2018/3/26 0026
 */
public interface TjsOrganPersonDao extends GenericDao<TjsOrganPerson,Long> {
    Page<TjsOrganPerson> findByOrganId(Long organId, Pageable pageable);
}
