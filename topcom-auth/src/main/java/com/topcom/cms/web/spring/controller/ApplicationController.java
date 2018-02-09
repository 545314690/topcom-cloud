package com.topcom.cms.web.spring.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.domain.Application;
import com.topcom.cms.service.ApplicationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 应用管理维护的交互控制
 *
 * @author lism
 */
@Controller
@RequestMapping("/admin/application/")
public class ApplicationController extends
        GenericController<Application, Long, ApplicationManager> {

    ApplicationManager applicationManager;

    @Autowired
    public void setApplicationManager(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
        this.manager = this.applicationManager;
    }
}
