package com.topcom.cms.yuqing.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.common.page.PageRequest;
import com.topcom.cms.domain.Group;
import com.topcom.cms.domain.User;
import com.topcom.cms.es.vo.AggPageImpl;
import com.topcom.cms.service.GroupManager;
import com.topcom.cms.service.UserManager;
import com.topcom.cms.web.bind.annotation.CurrentUser;
import com.topcom.cms.yuqing.domain.UserStyle;
import com.topcom.cms.yuqing.service.UserStyleManager;
import com.topcom.cms.yuqing.vo.request.GroupStyle;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/userStyle/")
public class UserStyleController extends
        GenericController<UserStyle, Long, UserStyleManager> {

    @Autowired
    GroupManager groupManager;
    @Autowired
    UserManager userManager;
    UserStyleManager userStyleManager;

    @Autowired
    public void setUserStyleManager(UserStyleManager userStyleManager) {
        this.userStyleManager = userStyleManager;
        this.manager = userStyleManager;
    }


    /**
     * 当user的userStyle不存在的时候返回group的
     * @param user
     * @param request
     * @param response
     * @return
     */
    @ApiOperation("findUserStyleByUser")
    @RequestMapping(
            value = {"findUserStyleByUser"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public UserStyle findUserStyleByUser(@CurrentUser User user,HttpServletRequest request, HttpServletResponse response) {
        UserStyle userStyle = this.userStyleManager.findByUserId(user.getId());
        user = userManager.findById(user.getId());
        if (userStyle==null){
            Set<Group> groups = user.getGroups();
            if (groups==null||groups.isEmpty()){
                return null;
            }
            Group group=  groups.iterator().next();
            userStyle = this.userStyleManager.findByGroupId(group.getId());
        }
        return userStyle;
    }

    @ApiOperation("保存userStyle，保证每个用户只有一个UserStyle")
    @RequestMapping(value = {"setUserStyle"},
            method = {RequestMethod.POST},
            produces = {"application/json"},
            consumes = {"application/json"})
    @ResponseBody
    public UserStyle setUserStyle(@CurrentUser User user,@RequestBody UserStyle userStyle) throws Exception {
        UserStyle us=this.userStyleManager.findByUserId(user.getId());
        if (us==null){
            Date date = new Date();
            userStyle.setDateCreated(date);
            userStyle.setDateModified(date);
            userStyle.setUserId(user.getId());
            userStyle = this.manager.save(userStyle);
            return userStyle;
        } else{
            userStyle.setId(us.getId());
            userStyle.setDateModified(new Date());
            userStyle.setUserId(user.getId());
            userStyle = this.manager.save(userStyle);
            return userStyle;
        }
    }

    @ApiOperation("group保存userStyle")
    @RequestMapping(value = {"setGroupUserStyle"},
            method = {RequestMethod.POST},
            produces = {"application/json"},
            consumes = {"application/json"})
    @ResponseBody
    public UserStyle setGroupUserStyle(@RequestBody UserStyle userStyle) {
        UserStyle us=this.userStyleManager.findByGroupId(userStyle.getGroupId());
        if (us==null){
            Date date = new Date();
            userStyle.setDateCreated(date);
            userStyle.setDateModified(date);
            userStyle = this.manager.save(userStyle);
            return userStyle;
        } else{
            userStyle.setId(us.getId());
            userStyle.setDateModified(new Date());
            userStyle = this.manager.save(userStyle);
            return userStyle;
        }
    }

    @ApiOperation("查询grop的userstyle")
    @RequestMapping(value = {"findAllGroupDefault"},
            method = {RequestMethod.POST},
            produces = {"application/json"},
            consumes = {"application/json"})
    @ResponseBody
    public Page<UserStyle> findAllGroupDefault(@RequestBody PageRequest page){
        return this.userStyleManager.findByGroupIdIsNotNull(page);
    }

    @ApiOperation("查询group列表")
    @RequestMapping(value = {"findAllGroupAndUsersyle"},
            method = {RequestMethod.POST},
            produces = {"application/json"},
            consumes = {"application/json"})
    @ResponseBody
    public Page findAllGroupAndUsersyle(@RequestBody PageRequest page){
        Page<Group> groupPage = groupManager.findAll(page.pageable());
        List<GroupStyle> contentList = new ArrayList<>();
        for (Group group:groupPage.getContent()){
            Long groupId = group.getId();
            UserStyle userStyle=this.userStyleManager.findByGroupId(groupId);
            GroupStyle groupStyle;
            if (userStyle!=null){
                groupStyle = new GroupStyle(group,userStyle);
            }else {
                groupStyle = new GroupStyle(group);
            }
            contentList.add(groupStyle);
        }
        Page result = new AggPageImpl<>(contentList,groupPage.getTotalPages(),groupPage.getTotalElements(),page.pageable());
        return result;
    }

    @ApiOperation("更新group列表")
    @RequestMapping(value = {"updateGroupAndStyle"},
            method = {RequestMethod.POST},
            produces = {"application/json"},
            consumes = {"application/json"})
    @ResponseBody
    public GroupStyle updateGroupAndStyle(@RequestBody GroupStyle groupStyle){
        Group group = groupStyle.getGroup();
        UserStyle style = groupStyle.getStyle();
        groupManager.saveGroup(group);
        if (style!=null){
            style.setGroupId(group.getId());
            UserStyle userStyle = this.setGroupUserStyle(style);
            groupStyle.setStyle(userStyle);
//            userStyleManager.save(style);
        }
        return groupStyle;
    }

    private GroupStyle addUserStyleToGroup(Group group){
        Long groupId = group.getId();
        UserStyle userStyle=this.userStyleManager.findByGroupId(groupId);
        GroupStyle result = new GroupStyle(group,userStyle);
//        JSONObject result = new JSONObject();
//        if (userStyle!=null){
//            result.put("userStyle",userStyle);
//        }
//        result.put("name",group.getName());
//        result.put("description",group.getDescription());
//        result.put("level",group.getLevel());
//        result.put("rolesNames",group.getRoleNames());
//        result.put("roles",group.getRoles());
//        result.put("id",group.getId());
//        result.put("dateCreated",group.getDateCreated());
//        result.put("dateModified",group.getDateModified());
//        result.put("deleted",group.getDeleted());
//        result.put("entityName",group.getEntityName());
//        result.put("text",group.getText());
        return result;
    }
}
