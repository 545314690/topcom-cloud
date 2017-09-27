package com.topcom.cms.web.spring.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.domain.Resource;
import com.topcom.cms.domain.User;
import com.topcom.cms.exception.BusinessException;
import com.topcom.cms.exception.ResponseData;
import com.topcom.cms.perm.token.TokenManager;
import com.topcom.cms.service.UserManager;
import com.topcom.cms.utils.PasswordHelper;
import com.topcom.cms.utils.PermissionUtils;
import com.topcom.cms.web.bind.annotation.CurrentUser;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 用户基本信息管理与维护的交互控制
 *
 * @author lsm
 */
@Controller
@RequestMapping("/perm/")
public class PermissionController{

    @Autowired
    PermissionUtils permissionUtils;


    /**
     * 校验是否登录和权限
     */
    @ApiOperation("校验权限")
    @RequestMapping(value = {"check"}, method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData check(@RequestParam String requestUri, @RequestParam String requestMethod, @RequestParam String token) throws Exception {
        boolean status = permissionUtils.checkPermission(requestUri, requestMethod, token);
        return new ResponseData(status);
    }


}
