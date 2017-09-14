package com.topcom.cms.yuqing.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.common.page.PageRequest;
import com.topcom.cms.domain.User;
import com.topcom.cms.web.bind.annotation.CurrentUser;
import com.topcom.cms.yuqing.domain.WarningLog;
import com.topcom.cms.yuqing.service.WarningLogManager;
import com.topcom.cms.yuqing.vo.request.WarningLogRequest;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/warningLog/")
@Api("预警日志接口")
public class WarningLogController extends GenericController<WarningLog, Long, WarningLogManager> {
    WarningLogManager warningLogManager;

    @Autowired
    public void setWarningLogManager(WarningLogManager warningLogManager) {
        this.warningLogManager = warningLogManager;
        this.manager = warningLogManager;
    }

    /**
     * 查询预警信息
     *
     * @return 分页数据
     */
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Page findWarningInfo(@PathVariable Long id, @RequestParam Integer page, @RequestParam Integer limit) {
        if (page == null) page = 1;
        if (limit == null) limit = 10;
        return warningLogManager.getWarningInfo(id, page, limit);
    }

    /**
     * 根据专题名字（模糊），日志生成时间和用户id查询预警日志信息
     *
     * @return 分页数据
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Page<WarningLog> searchWarningLog(@CurrentUser User user,
                                             @RequestBody WarningLogRequest request) {
        return warningLogManager.searchWarningLog(user.getId(), request.getSubjectName(),
                request.getDate().startDate(), request.getDate().endDate(),
                request.getPage().pageable());
    }

    /**
     * 根据专题查询预警日志信息
     *
     * @return 分页数据
     */
    @RequestMapping(value = "/subject/{subjectId}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Page<WarningLog> findSubjectWarningLog(@CurrentUser User user, @PathVariable Long subjectId, @RequestBody PageRequest pageRequest) {
        return warningLogManager.findBySubjectId(subjectId, pageRequest.pageable());
    }

    /**
     * 根据当前登陆查询预警日志信息
     *
     * @return 分页数据
     */
    @RequestMapping(value = "/findByUser/", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Page<WarningLog> findSubjectWarningLog(@CurrentUser User user, @RequestBody PageRequest pageRequest) {
        return warningLogManager.findByUserId(user.getId(), pageRequest.pageable());
    }

}
