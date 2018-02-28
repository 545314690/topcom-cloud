package com.topcom.bi.service;

import com.topcom.bi.domain.Interface;
import com.topcom.cms.mongo.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by lism on 17-10-31.
 *
 * @author lism
 */
public interface InterfaceService extends BaseService<Interface, String> {
    Page<Interface> findByNameLike(String name, Pageable pageable);
}
