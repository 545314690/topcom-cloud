package com.topcom.bi.dao;

import com.topcom.bi.domain.Page;
import com.topcom.cms.mongo.base.BaseDao;
import org.springframework.data.domain.Pageable;

/**
 * Created by lism on 17-10-31.
 */
public interface PageDao extends BaseDao<Page, String> {
    org.springframework.data.domain.Page<Page> findByNameLike(String name, Pageable pageable);
}
