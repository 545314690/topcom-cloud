package com.topcom.cms.web.spring.controller;

import com.topcom.cms.domain.User;
import com.topcom.cms.exception.ResponseData;
import com.topcom.cms.utils.PermissionUtils;
import com.topcom.cms.utils.SubjectUtil;
import com.topcom.cms.web.bind.annotation.CurrentUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户基本信息管理与维护的交互控制
 *
 * @author lsm
 */
@Controller
@RequestMapping("/perm/")
public class PermissionController{

    @Autowired
    private PermissionUtils permissionUtils;
    private SubjectUtil subjectUtil;

    /**
     * 校验是否登录和权限
     */
    @ApiOperation("校验权限")
    @RequestMapping(value = {"check"}, method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData check(@RequestParam String requestUri, @RequestParam String requestMethod, @RequestParam String accessToken) throws Exception {
        boolean status = permissionUtils.checkPermission(requestUri, requestMethod, accessToken);
        return new ResponseData(status);
    }
    /**
     * 获取当前登录用户
     */
    @ApiOperation("获取当前登录用户")
    @RequestMapping(value = {"loginUser"}, method = {RequestMethod.GET})
    @ResponseBody
    public User loginUser(@CurrentUser User user) throws Exception {
        return user;
    }
    /**
     * 获取当前登录用户id
     */
    @ApiOperation("获取当前登录用户id")
    @RequestMapping(value = {"loginUserId"}, method = {RequestMethod.GET})
    @ResponseBody
    public Long loginUserId(@CurrentUser User user) throws Exception {
        if(user!=null){
           return user.getId();
        }
        return null;
    }
    /**
     * 获取当前登录用户
     */
    @ApiOperation("获取当前登录用户")
    @RequestMapping(value = {"loginUserByToken"}, method = {RequestMethod.GET})
    @ResponseBody
    public User loginUserByToken(@RequestParam String accessToken) throws Exception {
        User user =  SubjectUtil.getCurrentUser(accessToken);
        return user;
    }
    /**
     * 获取当前登录用户id
     */
    @ApiOperation("获取当前登录用户id")
    @RequestMapping(value = {"loginUserIdByToken"}, method = {RequestMethod.GET})
    @ResponseBody
    public Long loginUserIdByToken( @RequestParam String accessToken) throws Exception {
        User user =  SubjectUtil.getCurrentUser(accessToken);
        if(user!=null){
            return user.getId();
        }
        return null;
    }
}
