package com.topcom.cms.yuqing.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.cms.yuqing.domain.Keywords;
import com.topcom.cms.yuqing.domain.KeywordsWeight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface KeywordsWeightManager extends GenericManager<KeywordsWeight, Long> {

	/**
	 * 根据用户id查询关键词
	 * @param pageable
	 * @param userId 
	 * @return
	 */
	Page<KeywordsWeight> findByUserId(Pageable pageable, Long userId);

	List<KeywordsWeight> findByUserId(Long userId);

    List<KeywordsWeight> findByUserIdAndType(Long id, KeywordsWeight.Type type);
}
