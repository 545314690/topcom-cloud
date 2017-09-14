package com.topcom.cms.yuqing.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.cms.yuqing.domain.Collection;
import com.topcom.cms.yuqing.domain.MediaType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 收藏管理业务层
 *
 * @author <a href="mailto:545314690@qq.om">lisenmiao</a>
 * @date 2014年10月11日下午3:42:41
 */
public interface CollectionManager extends GenericManager<Collection, Long> {

    Page<Collection> findByType(Pageable pageable, MediaType type, String keywords);

    Collection findByOId(String oId);


    Page<Collection> findByUserId(Pageable pageable, Long id);
}
