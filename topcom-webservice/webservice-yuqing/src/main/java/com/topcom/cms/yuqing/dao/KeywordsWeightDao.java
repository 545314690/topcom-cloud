package com.topcom.cms.yuqing.dao;

import com.topcom.cms.base.dao.GenericDao;
import com.topcom.cms.yuqing.domain.Keywords;
import com.topcom.cms.yuqing.domain.KeywordsWeight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface KeywordsWeightDao extends GenericDao<KeywordsWeight, Long> {

    Page<KeywordsWeight> findByUserId(Pageable pageable, Long userId);

    List<KeywordsWeight> findByUserId(Long userId);

    List<KeywordsWeight> findByUserIdAndType(Long userId, KeywordsWeight.Type type);
}
