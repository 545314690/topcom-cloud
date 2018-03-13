package com.topcom.cms.web.spring.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.domain.ViewLog;
import com.topcom.cms.service.ViewLogManager;
import com.topcom.cms.vo.ViewLogDto;
import com.topcom.cms.vo.ViewLogRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 访问日志接口
 */
@Controller
@RequestMapping("/viewLog")
public class ViewLogController extends GenericController<ViewLog, Long,ViewLogManager> {

    private ViewLogManager viewLogManager;

    @Autowired
    public void setViewLogManager(ViewLogManager viewLogManager) {
        this.viewLogManager = viewLogManager;
        this.manager = this.viewLogManager;
    }

    /**
     * 访问量前n 名的资源
     * @param request
     * @return
     */
    @ApiOperation("访问量前limit名的资源")
    @RequestMapping(value = {"/searchTop"}, method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List<ViewLogDto> searchTop(@ApiParam("参数") @RequestBody ViewLogRequest request) {

        List<ViewLogDto> viewLogDtoList = this.viewLogManager.searchHotPage(request);
        if (viewLogDtoList.size()<=request.getLimit()){
            return viewLogDtoList;
        }else {
            return viewLogDtoList.subList(0,request.getLimit());
        }
    }
}
