package com.topcom.bi.dao;

import com.topcom.bi.domain.Interface;
import com.topcom.cms.mongo.base.BaseDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by lism on 17-10-31.
 */
public interface InterfaceDao extends BaseDao<Interface, String> {
    Page<Interface> findByNameLike(String name, Pageable pageable);
}
