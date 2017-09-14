package com.topcom.cms.web.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.domain.OperationLog;
import com.topcom.cms.service.OperationLogManager;

/**
 * 系统操作日志信息管理维护的交互控制
 * 
 * @author maodaqiang
 * 
 */
@Controller
@RequestMapping("/operationlog/")
public class OperationLogController extends
		GenericController<OperationLog, Long, OperationLogManager> {
	OperationLogManager operationLogManager;

	@Autowired
	public void setOperationLogManager(OperationLogManager operationLogManager) {
		this.operationLogManager = operationLogManager;
		this.manager = this.operationLogManager;
	}
}
