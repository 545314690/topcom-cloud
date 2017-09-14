package com.topcom.cms.yuqing.dao;

import com.topcom.cms.base.dao.GenericDao;
import com.topcom.cms.yuqing.domain.Keywords;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface KeywordsDao extends GenericDao<Keywords, Long> {

    Page<Keywords> findByUserId(Pageable pageable, Long userId);

    List<Keywords> findByUserId(Long userId);

    List<Keywords> findByUserIdAndType(Long userId, Keywords.Type type);
}
