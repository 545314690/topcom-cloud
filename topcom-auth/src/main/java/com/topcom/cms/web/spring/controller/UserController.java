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
import net.sf.json.JSONObject;
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
@RequestMapping("/admin/user/")
public class UserController extends GenericController<User, Long, UserManager> {

    @Autowired
    TokenManager tokenManager;
    UserManager userManager;

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
        this.manager = this.userManager;
    }


    /**
     * 重写父类get方法 加入模糊查询功能
     */
//    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/", produces = "application/json")
    @ResponseBody
    public Page<User> get(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        String FnameAndCode = request.getParameter("fac");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        if (StringUtils.isNotBlank(page)) {
            this.pageNumber = (Integer.valueOf(page).intValue() - 1);
        } else {
            this.pageNumber = 0;
        }
        if (StringUtils.isNotBlank(limit) && !limit.equalsIgnoreCase("NaN")) {
            this.pageSize = Integer.valueOf(limit).intValue();
        } else {
            this.pageSize = Integer.valueOf(20).intValue();
        }
        this.pageable = new PageRequest(this.pageNumber, this.pageSize,
                new Sort(Sort.Direction.ASC, new String[]{"dateCreated"}));
        if (StringUtils.isNotBlank(name)) {
            this.page = this.manager.fuzzyQueryByName(this.pageable, name);
        } else if (StringUtils.isNotBlank(fullName)) {
            this.page = this.manager.fuzzyQueryByFullName(this.pageable, fullName);
        } else if (StringUtils.isNotBlank(FnameAndCode)) {
            this.page = this.manager.fuzzyQueryByFAC(this.pageable, FnameAndCode);
        } else {
            this.page = userManager.findAll(this.pageable);
        }

        logger.info(this.page);
        return this.page;
    }


    /**
     * validate 人员编码 to be unique
     *
     * @return Map对象
     */
    @RequestMapping(value = "/checkCode{code}", method = RequestMethod.POST)
    @ResponseBody
    public boolean checkRegisterUserCode(@PathVariable String code) {
        User u = this.manager.findUserByCode(code);
        if (u != null) {
            return true;// code exists
        }
        return false;
    }

    /**
     * 重写父类put方法 更改了父类的保存方法
     */
    @RequestMapping(value = {"/{id}"}, method = {
            RequestMethod.PUT}, produces = {"application/json"}, consumes = {
            "application/json"})
    @ResponseBody
    public User update(@PathVariable Long id, @RequestBody User model) {
        User user = this.manager.findById(id);
        if (!StringUtils.equals(model.getPassword(), user.getPassword())) {
            // 如果更改了密码，新密码加密
//            model.encodePassword();
            PasswordHelper.encryptPassword(model);
        }else {
            //保存之前的salt
            model.setSalt(user.getSalt());
        }
        model.setId(Long.valueOf(id.toString()));
        model.setDateModified(new Date());
        model = this.manager.save(model);
        if (!StringUtils.equals(model.getPassword(), user.getPassword())) {
            tokenManager.deleteToken(model.getId());
        }
        return model;
    }

    /**
     * 重写父类Get方法，提高查询的效率
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody

    public User get(@PathVariable Long id) {
        return userManager.findById(id);

    }

    /**
     * 重写父类post方法 更改了父类的保存方法
     */
    @RequestMapping(value = {"/"}, method = {RequestMethod.POST}, produces = {"application/json"}, consumes = {"application/json"})
    @ResponseBody
    public User create(@RequestBody User model) throws BusinessException {
        try {
            Date date = new Date();
            model.setDateCreated(date);
            model.setDateModified(date);
            PasswordHelper.encryptPassword(model);
            model = this.manager.save(model);
        } catch (Exception e) {
            if (e.getCause().getClass().equals(ConstraintViolationException.class)) {
                throw new BusinessException("用户名已存在");
            }
            throw new BusinessException("增加失败");
        }
        return model;
    }


    /**
     * 根据用户名查询用户是否存在
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/exists/{username}", method = RequestMethod.GET)
    @ResponseBody
    public boolean exists(@PathVariable String username) {
        User u = this.manager.findByUsername(username);
        if (u == null) {
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap modifyPassword(@CurrentUser User user, HttpServletRequest request, @RequestParam String oldPwd, @RequestParam String newPwd) throws BusinessException {
        ModelMap modelMap = new ModelMap("success", false);
        //保持的旧密码
        String savedPwd = user.getPassword();
        //加密接收的就密码
        String encodedOldPwd = PasswordHelper.getEncodedPassword(oldPwd, user.getCredentialsSalt());
        //对比
        if (StringUtils.equals(savedPwd, encodedOldPwd)) {
            user.setPassword(newPwd);
            PasswordHelper.encryptPassword(user);
            int i = userManager.updatePassword(
                    user.getId(), user.getPassword(),
                    user.getSalt());
            if (i > 0) {
                modelMap.put("success", true);
                //密码修改成功，需要重新登陆
                //TODO://
                tokenManager.deleteToken(user.getId());
            }
        } else {
            throw new BusinessException("原密码错误");
        }
        return modelMap;
    }

    @RequestMapping(value = "/modifyState/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap modifyState(HttpServletRequest request, @PathVariable Long userId, @RequestParam User.State state) throws BusinessException {
        ModelMap modelMap = new ModelMap("success", false);
        int i = userManager.updateState(userId, state);
        if (i > 0) {
            modelMap.put("success", true);
        } else {
            throw new BusinessException("更改状态失败");
        }
        return modelMap;
    }

    /**
     * 根据组id获取该组下面的用户
     *
     * @param request
     * @param response
     * @param groupId
     * @return list
     */
    @RequestMapping(value = {"/getUsersByGroupId"}, method = {
            RequestMethod.GET}, produces = {"application/json"})
    @ResponseBody
    public List<User> getUsersByGroupId(HttpServletRequest request, HttpServletResponse response,
                                        @RequestParam Long groupId) {
        return userManager.getUsersByGroupId(groupId);

    }

    /**
     * 根据组id获取该组下面的用户
     *
     * @param request
     * @param response
     * @param groupId  用户名模糊查询
     * @return 分页数据
     */
    @RequestMapping(value = {"/getPageUsersByGroupId"}, method = {
            RequestMethod.GET}, produces = {"application/json"})
    @ResponseBody
    public Page<User> getPageUsersByGroupId(HttpServletRequest request, HttpServletResponse response,
                                            @RequestParam Long groupId) {
        String name = request.getParameter("name");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        if (StringUtils.isNotBlank(page)) {
            this.pageNumber = (Integer.valueOf(page).intValue() - 1);
        } else {
            this.pageNumber = 0;
        }
        if (StringUtils.isNotBlank(limit)) {
            this.pageSize = Integer.valueOf(limit).intValue();
        }
        this.pageable = new PageRequest(this.pageNumber, this.pageSize,
                new Sort(Sort.Direction.ASC, new String[]{"id"}));
        if (StringUtils.isBlank(name)) {
            name = "";
        }
        this.page = this.manager.getPageUsersByGroupIdAndName(this.pageable, groupId, name);
        logger.info(this.page);
        return this.page;
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

    private Resource getResourceParent(Resource resource){
        Resource parent = resource.getParent();
        if (parent!=null){
            List<Resource> resourceList = new ArrayList<>();
            resourceList.add(resource);
            parent.setChildren(resourceList);
            return getResourceParent(parent);
        }else {
            return resource;
        }
    }

    private Resource mergeChild(Resource resource1,Resource resource2){
        List<Resource> children1 = resource1.getChildren();
        List<Resource> children2 = resource2.getChildren();
        if (children1==null){
            return resource2;
        }else {
            if (children2==null){
                return resource1;
            }else {
                children1.addAll(children2);
                Map<Long,Resource> resultMap = new HashMap<>();
                for (int i=0;i<children1.size();i++){
                    Resource resource_i = children1.get(i);
                    Long id_i = resource_i.getId();
                    for (int j=i+1;j<children1.size();j++){
                        Resource resource_j = children1.get(j);
                        Long id_j = resource_j.getId();
                        if (id_i == id_j){
                            resource_i = mergeChild(resource_i,resource_j);
                        }
                    }
                    if (!resultMap.keySet().contains(id_i)){
                        resultMap.put(id_i,resource_i);
                    }
                }
                children1.clear();
                for (Long l:resultMap.keySet()){
                    children1.add(resultMap.get(l));
                }
                resource1.setChildren(children1);
                return resource1;
            }
        }
    }
    private List<Resource> mergeChild(List<Resource> resourceList){
        Map<Long,Resource> resultMap = new HashMap<>();
        for (int i=0;i<resourceList.size();i++){
            if (i==23){
                System.out.println("23");
            }
            Resource resource_i = resourceList.get(i);
            Long resourceId_i = resource_i.getId();
            for (int j=i+1;j<resourceList.size();j++){
                Resource resource_j = resourceList.get(j);
                Long resourceId_j = resource_j.getId();
                if (resourceId_i==resourceId_j){
//                    List<Resource> childrenList = resource_i.getChildren();
//                    childrenList.addAll(resource_j.getChildren());
                    resource_i=mergeChild(resource_i,resource_j);
                }
            }
            if (!resultMap.keySet().contains(resourceId_i)){
                resultMap.put(resourceId_i,resource_i);
            }
        }
        resourceList.clear();
        for (Long l:resultMap.keySet()){
            resourceList.add(resultMap.get(l));
        }
        return resourceList;
    }


    private List<Resource> getTreeOn( List<Resource> resourceList){
        Map<Long,Resource> resultMap = new HashedMap();
        for (int i = 0;i<resourceList.size();i++){
            Resource resource_i=resourceList.get(i);
            Resource superParent_i = getResourceParent(resource_i);
            Long parentId_i = superParent_i.getId();
            for (int j=i+1;j<resourceList.size();j++){
                Resource resource_j=resourceList.get(j);
                superParent_i = megerBtoA(superParent_i,resource_j);
            }
            if (!resultMap.keySet().contains(parentId_i)){
                resultMap.put(parentId_i,superParent_i);
            }
        }
        resourceList.clear();
        for (Long l:resultMap.keySet()){
            resourceList.add(resultMap.get(l));
        }
        return resourceList;
    }

    private Resource megerBtoA(Resource resource_a,Resource resource_b){
        Long aId = resource_a.getId();
        Long bId = resource_b.getId();
        Long parentId_b = resource_b.getParentId();
        if (aId==bId){
            return resource_b;
        }
        if (parentId_b==null){
            return resource_a;
        }
        List<Resource> childrenList = resource_a.getChildren();
        if (parentId_b==aId){
            childrenList = addToList(childrenList,resource_b);
        }else {
            for (int i=0;i<childrenList.size();i++){
                Resource resouce_i = megerBtoA(childrenList.get(i),resource_b);
                childrenList = addToList(childrenList,resouce_i);
            }
        }
        resource_a.setChildren(childrenList);
        return resource_a;
    }
    private List<Resource> addToList(List<Resource> resourceList,Resource resource){
        Long id = resource.getId();
        List<Resource> result = new ArrayList<>();
        for (int i=0;i<resourceList.size();i++){
            Long id_i = resourceList.get(i).getId();
            if (id==id_i){
                result.add(resource);
            }else {
                result.add(resourceList.get(i));
            }
        }
        return result;
    }
}
