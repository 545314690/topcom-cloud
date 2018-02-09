package com.topcom.cms.web.spring.controller;

import com.topcom.cms.base.web.spring.controller.GenericControllerTestCase;
import com.topcom.cms.domain.User;
import com.topcom.cms.service.UserManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserControllerTest extends GenericControllerTestCase {

	private UserController userController;
	UserManager manager;

	@Autowired
	public void setUserController(UserController userController) {
		this.userController = userController;
	}

	@Autowired
	public void setManager(UserManager manager) {
		this.manager = manager;
	}
	@Test
	public void test() throws Exception {
		User model = new User();
		this.userController.create(model);

	}
	
	@Test
	public void login() throws Exception {
		User model = new User();
		this.userController.create(model);

	}

}
