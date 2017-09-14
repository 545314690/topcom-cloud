package com.topcom.cms.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.cms.domain.OperationLog;

/**
 * 系统操作日志访问接口类
 * 
 * @author maodaqiang
 * 
 */
public interface OperationLogManager extends GenericManager<OperationLog, Long> {

	/**
	 * 通过id 删除相应操作日志
	 * 
	 * @param id
	 */
	void deleteOperationLog(Long id);

	OperationLog persist(OperationLog ol);
}
