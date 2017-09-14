package com.topcom.cms.yuqing.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.cms.common.page.PageRequest;
import com.topcom.cms.yuqing.dao.UserStyleDao;
import com.topcom.cms.yuqing.domain.UserStyle;
import com.topcom.cms.yuqing.service.UserStyleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "userStyleManager")
@Transactional
public class UserStyleManagerImpl extends GenericManagerImpl<UserStyle, Long>
        implements UserStyleManager {
    UserStyleDao userStyleDao;

    @Autowired
    public void setUserStyleDao(UserStyleDao userStyleDao) {
        this.userStyleDao = userStyleDao;
        this.dao = this.userStyleDao;
    }

    @Override
    public UserStyle findByUserId(Long id) {
        return this.userStyleDao.findByUserId(id);
    }

    @Override
    public UserStyle findByGroupId(Long id) {
        return this.userStyleDao.findByGroupId(id);
    }

    @Override
    public Page<UserStyle> findByGroupIdIsNotNull(PageRequest page) {
        return this.userStyleDao.findByGroupIdIsNotNull(page.pageable());
    }
}
