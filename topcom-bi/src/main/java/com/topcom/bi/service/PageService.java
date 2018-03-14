package com.topcom.bi.service;

import com.topcom.bi.domain.Page;
import com.topcom.cms.mongo.base.BaseService;
import org.springframework.data.domain.Pageable;

/**
 * Created by lism on 17-10-31.
 *
 * @author lism
 */
public interface PageService extends BaseService<Page, String> {
    org.springframework.data.domain.Page<Page> findByNameLike(String name, Pageable pageable);
}
