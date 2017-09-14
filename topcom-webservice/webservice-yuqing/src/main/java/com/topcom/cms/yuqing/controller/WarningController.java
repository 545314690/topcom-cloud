package com.topcom.cms.yuqing.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.yuqing.domain.WarningTask;
import com.topcom.cms.yuqing.service.WarningManager;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/warning/")
@Api("预警接口")
public class WarningController extends GenericController<WarningTask, Long, WarningManager> {
    WarningManager warningManager;

    @Autowired
    public void setWarningManager(WarningManager warningManager) {
        this.warningManager = warningManager;
        this.manager = warningManager;
    }

}
