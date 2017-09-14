package com.topcom.cms.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.cms.dao.AuthorizationDao;
import com.topcom.cms.domain.Authorization;
import com.topcom.cms.service.AuthorizationManager;

/**
 * 授权——服务实现类
 * 
 * @author liuxiaoming
 * 
 */
@Service
@Transactional
public class AuthorizationManagerImpl extends
		GenericManagerImpl<Authorization, Long> implements AuthorizationManager {

	AuthorizationDao authorizationDao;
	
	@Autowired
	public void setAuthorizationDao(AuthorizationDao authorizationDao) {
		this.authorizationDao = authorizationDao;
		this.dao = this.authorizationDao;
	}

}
