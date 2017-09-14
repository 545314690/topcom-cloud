package com.topcom.cms.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topcom.cms.base.service.GenericManagerTestCase;
import com.topcom.cms.domain.User;
import com.topcom.cms.service.UserManager;

public class UserManagerTest extends
		GenericManagerTestCase<Long, User, UserManager> {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(UserManagerTest.class);

	UserManager userManager;

	public UserManagerTest() {
		super(User.class);
	}

	@Autowired
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
		this.manager = this.userManager;
	}

	@Test
	public void test() {
		List<User> result = this.manager.findAll();
		if (logger.isInfoEnabled()) {
			logger.info("test() - List<User> result=" + result); //$NON-NLS-1$
		}
	}

}
