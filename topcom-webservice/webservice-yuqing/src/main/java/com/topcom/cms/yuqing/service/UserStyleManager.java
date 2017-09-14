package com.topcom.cms.yuqing.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.cms.common.page.PageRequest;
import com.topcom.cms.yuqing.domain.UserStyle;
import org.springframework.data.domain.Page;

public interface UserStyleManager extends GenericManager<UserStyle, Long> {


	UserStyle findByUserId(Long id);

	UserStyle findByGroupId(Long id);

	Page<UserStyle> findByGroupIdIsNotNull(PageRequest page);
}
