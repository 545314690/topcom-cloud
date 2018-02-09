package com.topcom.cms.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.cms.domain.Application;
import org.springframework.transaction.annotation.Transactional;

/**
 * 应用接口
 * 
 * @author lism
 * 
 */
@Transactional
public interface ApplicationManager extends GenericManager<Application, Long> {


    Application findByName(String name);
}
