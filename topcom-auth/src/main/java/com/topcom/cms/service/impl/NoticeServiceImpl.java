package com.topcom.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topcom.cms.base.dao.GenericDao;
import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.cms.dao.NoticeDao;
import com.topcom.cms.domain.Notice;
import com.topcom.cms.service.NoticeManager;

/**
 * 登录日志访问实现类
 * 
 * @author lism
 * 
 */
@Service(value = "noticeManager")
@Transactional
public class NoticeServiceImpl extends GenericManagerImpl<Notice, Long>
		implements NoticeManager {

	GenericDao<Notice, Long> noticeDao;

	@Autowired
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
		this.dao = this.noticeDao;
	}

	@Override
	public void deleteNotice(Long id) {
		noticeDao.delete(id);
	}

}
