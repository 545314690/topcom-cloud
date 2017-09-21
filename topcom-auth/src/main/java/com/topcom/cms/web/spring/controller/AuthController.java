package com.topcom.cms.web.spring.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.domain.Resource;
import com.topcom.cms.domain.User;
import com.topcom.cms.exception.BusinessException;
import com.topcom.cms.perm.token.TokenManager;
import com.topcom.cms.service.UserManager;
import com.topcom.cms.utils.PasswordHelper;
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
@RequestMapping("/auth/")
public class AuthController extends GenericController<User, Long, UserManager> {

    @Autowired
    TokenManager tokenManager;
    UserManager userManager;

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
        this.manager = this.userManager;
    }



    /**
     * 返回登录用户的resource
     */
    @ApiOperation("获取resource")
    @RequestMapping(
            value = {"resource"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public List<Resource> resource(@CurrentUser User user) {
        User user1 = this.manager.findById(user.getId());//缓存user懒加载，没有resource，需要在数据库查询
        //user.getPermissionNames();
        List<Resource> resourceList = user1.getResource();
        if (resourceList==null||resourceList.size()==0){
            return null;
        }
        return resourceList;
    }


}
