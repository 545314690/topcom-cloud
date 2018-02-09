package com.topcom.cms.web.spring.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.domain.Role;
import com.topcom.cms.exception.BusinessException;
import com.topcom.cms.service.RoleManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 角色基本信息管理维护的交互控制
 *
 * @author lsm
 */
@Controller
@RequestMapping("/admin/role/")
public class RoleController extends GenericController<Role, Long, RoleManager> {

    RoleManager roleManager;

    @Autowired
    public void setRoleManager(RoleManager roleManager) {
        this.roleManager = roleManager;
        this.manager = this.roleManager;
    }


    /**
     * 重写父类get方法 缓存role
     */
    @Override
    @RequestMapping(value = {"/"}, method = {RequestMethod.GET}, produces = {"application/json"})
    @ResponseBody
    public Page<Role> get(HttpServletRequest request,
                          HttpServletResponse response) {
        String roleName = request.getParameter("name");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        if (StringUtils.isNotBlank(page)) {
            pageNumber = (Integer.valueOf(page).intValue() - 1);
        } else {
            pageNumber = 0;
        }
        if (StringUtils.isNotBlank(limit)) {
            pageSize = Integer.valueOf(limit).intValue();
        }
        Pageable pageable = new PageRequest(pageNumber, pageSize,
                new Sort(Sort.Direction.DESC, new String[]{"id"}));
        Page rolePage = null;
        if (StringUtils.isBlank(roleName)) {
            rolePage = this.manager.findAllRole(pageable);
        } else {
            rolePage = this.manager.findByNameLike(pageable, roleName);
        }
        return rolePage;
    }

    /**
     * 重写父类post方法
     */
    @Override
    @RequestMapping(value = {"/"}, method = {RequestMethod.POST}, produces = {"application/json"}, consumes = {"application/json"})
    @ResponseBody
    public Role create(@RequestBody Role model) throws BusinessException {
        if (roleManager.findByName(model.getName()) != null) {
            throw new BusinessException("角色已存在");
        }
        Date date = new Date();
        model.setDateCreated(date);
        model.setDateModified(date);
        model = roleManager.saveRole(model);
        return model;
    }

    /**
     * 重写父类put方法 修改角色信息使用 无models
     */
    @Override
    @RequestMapping(value = {"/{id}"}, method = {RequestMethod.PUT}, produces = {"application/json"}, consumes = {"application/json"})
    @ResponseBody
    public Role update(@PathVariable Long id, @RequestBody Role model) {
        model.setId(Long.valueOf(id.toString()));
        model.setDateModified(new Date());
        model = roleManager.saveRole(model);
        return model;
    }

    /**
     * 重写父类put方法 修改角色信息使用 无models
     */
    @RequestMapping(value = {"/update/{id}"}, method = {RequestMethod.PUT}, produces = {"application/json"}, consumes = {"application/json"})
    @ResponseBody
    public Role updateRoleModel(@PathVariable Long id, @RequestBody Role model) {
        model.setId(Long.valueOf(id.toString()));
        model.setDateModified(new Date());
        model = roleManager.saveRole(model);
        return model;
    }

    /**
     * 重写父类delete方法
     */
    @Override
    @RequestMapping(value = {"/{id}"}, method = {RequestMethod.DELETE}, produces = {"application/json"})
    @ResponseBody
    public void delete(@PathVariable Long id) throws IOException {
        this.manager.deleteRole(id);
    }

    /**
     * 查找所有可用的角色
     */
    @RequestMapping(value = "/findAllUsableRoles", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Role> findAllUsableRoles() {
        return roleManager.findAllUsableRoles();
    }

    /**
     * 根据用户ID查找用户角色信息集合
     *
     * @param id
     * @return 用户对象集合
     */
    @RequestMapping(value = "/findRolesByGroupId", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Role> findRolesByGroupId(@RequestParam Long id) {
        return roleManager.findRolesByGroupId(id);
    }

    /**
     * 查询是否存在
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/exists/{name}", method = RequestMethod.GET)
    @ResponseBody
    public boolean exists(@PathVariable String name) {
        Role role = this.manager.findByName(name);
        if (role != null) {
            return false;
        }
        return true;
    }
}
