package com.topcom.cms.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.cms.dao.AuthenticationDao;
import com.topcom.cms.domain.Authentication;
import com.topcom.cms.service.AuthenticationManager;

/**
 * 认证——服务实现类
 * 
 * @author liuxiaoming
 * 
 */
@Service
@Transactional
public class AuthenticationManagerImpl extends
		GenericManagerImpl<Authentication, Long> implements
		AuthenticationManager {

	AuthenticationDao authenticationDao;

	public void setAuthenticationDao(AuthenticationDao authenticationDao) {
		this.authenticationDao = authenticationDao;
		this.dao = this.authenticationDao;
	}
}
