package com.topcom.bi.service;

import com.topcom.bi.domain.ItemType;
import com.topcom.cms.mongo.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by lism on 17-10-31.
 *
 * @author lism
 */
public interface ItemTypeService extends BaseService<ItemType, String> {
    Page<ItemType> findByNameLike(String name, Pageable pageable);
}
