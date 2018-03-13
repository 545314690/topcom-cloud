package com.topcom.cms.web.spring.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.domain.Resource;
import com.topcom.cms.domain.User;
import com.topcom.cms.domain.ViewLog;
import com.topcom.cms.service.UserManager;
import com.topcom.cms.service.ViewLogManager;
import com.topcom.cms.vo.ViewLogDto;
import com.topcom.cms.vo.ViewLogRequest;
import com.topcom.cms.web.bind.annotation.CurrentUser;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 访问日志接口
 */
@Controller
@RequestMapping("/viewLog")
public class ViewLogController extends GenericController<ViewLog, Long,ViewLogManager> {

    @Autowired
    UserManager userManager;
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
    public List<ViewLogDto> searchTop(@ApiParam("参数") @RequestBody ViewLogRequest request) {

        List<ViewLogDto> viewLogDtoList = this.viewLogManager.searchHotPage(request);
        if (viewLogDtoList.size()<=request.getLimit()){
            return viewLogDtoList;
        }else {
            return viewLogDtoList.subList(0,request.getLimit());
        }
    }

    @ApiOperation("访问量前limit名的资源")
    @RequestMapping(value = {"/topByUser"}, method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<ViewLogDto> topByUser(@CurrentUser User user, @RequestParam(required = false) Integer limit) {
        if (limit==null||limit==0){
            limit=20;
        }
        User user1 = this.userManager.findById(user.getId());//缓存user懒加载，没有resource，需要在数据库查询
        //user.getPermissionNames();
        Set<Resource> resourceSet = user1.getResource();
        if (resourceSet==null){
            return null;
        }else {
            ViewLogRequest request = new ViewLogRequest();
            request.setLimit(limit);
            List<Long>  resourceIds = new ArrayList<>();
            for (Resource r:resourceSet){
                resourceIds.add(r.getId());
            }
            request.setResourceIds(resourceIds);
            List<ViewLogDto> viewLogDtoList = this.viewLogManager.searchHotPage(request);
            if (viewLogDtoList.size()<limit){
                return viewLogDtoList;
            }else {
                return viewLogDtoList.subList(0,request.getLimit());
            }
        }
    }
}
