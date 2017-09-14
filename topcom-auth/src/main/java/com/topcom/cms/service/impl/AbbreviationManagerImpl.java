package com.topcom.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topcom.cms.base.service.impl.GenericTreeManagerImpl;
import com.topcom.cms.dao.AbbreviationDao;
import com.topcom.cms.domain.Abbreviation;
import com.topcom.cms.service.AbbreviationManager;

/**
 * 简称信息访问实现类
 * 
 * @author maodaqiang
 * 
 */
@Service(value = "abbreviationManager")
@Transactional
public class AbbreviationManagerImpl extends
		GenericTreeManagerImpl<Abbreviation, Long> implements
		AbbreviationManager {

	AbbreviationDao abbreviationDao;

	@Autowired
	public void setUserDao(AbbreviationDao abbreviationDao) {
		this.abbreviationDao = abbreviationDao;
		this.dao = this.abbreviationDao;
		this.treeDao = this.abbreviationDao;
	}

}
