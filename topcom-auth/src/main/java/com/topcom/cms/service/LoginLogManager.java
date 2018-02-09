package com.topcom.cms.service;

import org.springframework.transaction.annotation.Transactional;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.cms.domain.LoginLog;

/**
 * 登录日志信息接口
 * 
 * @author lism
 * 
 */
@Transactional
public interface LoginLogManager extends GenericManager<LoginLog, Long> {

	/**
	 * 通过ID 删除相应登录日志
	 * 
	 * @param id
	 */
	void deleteLoginLog(Long id);

}
