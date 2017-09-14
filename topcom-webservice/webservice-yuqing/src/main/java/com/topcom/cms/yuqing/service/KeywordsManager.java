package com.topcom.cms.yuqing.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.cms.yuqing.domain.Keywords;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface KeywordsManager extends GenericManager<Keywords, Long> {

	/**
	 * 根据用户id查询关键词
	 * @param pageable
	 * @param userId 
	 * @return
	 */
	Page<Keywords> findByUserId(Pageable pageable, Long userId);

	List<Keywords> findByUserId(Long userId);

    List<Keywords> findByUserIdAndType(Long id, Keywords.Type type);
}
