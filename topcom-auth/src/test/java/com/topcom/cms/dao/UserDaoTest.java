package com.topcom.cms.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topcom.cms.base.dao.GenericDaoTestCase;
import com.topcom.cms.dao.UserDao;
import com.topcom.cms.domain.User;

public class UserDaoTest extends GenericDaoTestCase<Long, User, UserDao> {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UserDaoTest.class);

	UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
		this.dao = this.userDao;
	}

	public UserDaoTest() {
		super(User.class);
	}

	@Test
	public void test() {
		List<User> result = this.userDao.findAll();
		if (logger.isInfoEnabled()) {
			logger.info("test() - List<User> result=" + result); //$NON-NLS-1$
		}
	}
}
