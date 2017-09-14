package com.topcom.cms.web.spring.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.domain.LoginLog;
import com.topcom.cms.service.LoginLogManager;

/**
 * 登录日志信息管理维护的交互控制
 * 
 * @author maodaqiang
 * 
 */
@Controller
@RequestMapping("/loginlog/")
public class LoginLogController extends
		GenericController<LoginLog, Long, LoginLogManager> {
	LoginLogManager loginLogManager;

	@Autowired
	public void setLoginLogManager(LoginLogManager loginLogManager) {
		this.loginLogManager = loginLogManager;
		this.manager = this.loginLogManager;
	}

	@RequestMapping(value = { "/{id}" }, method = { org.springframework.web.bind.annotation.RequestMethod.DELETE })
	@ResponseBody
	public void delete(@PathVariable Long id) throws IOException {
		this.manager.deleteLoginLog(id);
	}

}
