//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.topcom.cms.dao;

import com.topcom.cms.base.dao.GenericDao;
import com.topcom.cms.domain.Application;

public interface ApplicationDao extends GenericDao<Application, Long> {
    Application findByName(String name);
}
