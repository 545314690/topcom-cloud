package com.topcom.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topcom.cms.base.dao.GenericDao;
import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.cms.dao.LoginLogDao;
import com.topcom.cms.domain.LoginLog;
import com.topcom.cms.service.LoginLogManager;

/**
 * 登录日志访问实现类
 * 
 * @author maodaqiang
 * 
 */
@Service(value = "loginLogManager")
@Transactional
public class LoginLogServiceImpl extends GenericManagerImpl<LoginLog, Long>
		implements LoginLogManager {	

	GenericDao<LoginLog, Long> loginLogDao;

	@Autowired
	public void setLoginLogDao(LoginLogDao loginLogDao) {
		this.loginLogDao = loginLogDao;
		this.dao = this.loginLogDao;
	}

	@Override
	public void deleteLoginLog(Long id) {
		loginLogDao.delete(id);
	}

}
