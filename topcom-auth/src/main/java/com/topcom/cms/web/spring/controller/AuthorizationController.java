package com.topcom.cms.web.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.domain.Authorization;
import com.topcom.cms.service.AuthorizationManager;

/**
 * 授权——访问控制实现类
 * 
 * @author liuxiaoming
 * 
 */
@Controller
@RequestMapping("/authorization")
public class AuthorizationController extends
		GenericController<Authorization, Long, AuthorizationManager> {

}
