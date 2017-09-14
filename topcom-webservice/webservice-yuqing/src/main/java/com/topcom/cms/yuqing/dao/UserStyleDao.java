package com.topcom.cms.yuqing.dao;

import com.topcom.cms.base.dao.GenericDao;
import com.topcom.cms.yuqing.domain.Keywords;
import com.topcom.cms.yuqing.domain.UserStyle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserStyleDao extends GenericDao<UserStyle, Long> {


    UserStyle findByUserId(Long UserId);

    UserStyle findByGroupId(Long id);

    Page<UserStyle> findByGroupIdIsNotNull(Pageable pageable);
}
