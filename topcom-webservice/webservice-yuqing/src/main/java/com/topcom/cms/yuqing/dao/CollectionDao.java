package com.topcom.cms.yuqing.dao;

import com.topcom.cms.base.dao.GenericDao;
import com.topcom.cms.yuqing.domain.Collection;
import com.topcom.cms.yuqing.domain.MediaType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * 收藏dao层
 *
 * @author <a href="mailto:545314690@qq.om">lisenmiao</a>
 * @date 2015年2月25日下午2:31:27
 */
public interface CollectionDao extends GenericDao<Collection, Long> {

    /**
     * 根据类型查询
     *
     * @param pageable
     * @param type
     * @param keywords
     * @return
     */
    @Query("from Collection c where c.type = ?1 and c.title like %?2%")
    Page<Collection> findByType(Pageable pageable, MediaType type, String keywords);

    /**
     * 根据sorlId查询
     *
     * @param solrId
     * @return
     */
    Collection findByOId(String solrId);

    Page<Collection> findByUserId(Pageable pageable, Long id);
}
