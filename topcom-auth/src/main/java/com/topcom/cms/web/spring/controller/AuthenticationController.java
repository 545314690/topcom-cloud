package com.topcom.cms.web.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.domain.Authentication;
import com.topcom.cms.service.AuthenticationManager;

/**
 * 认证——访问控制实现类
 * 
 * @author liuxiaoming
 * 
 */
@Controller
@RequestMapping("/authentication")
public class AuthenticationController extends
		GenericController<Authentication, Long, AuthenticationManager> {

}
