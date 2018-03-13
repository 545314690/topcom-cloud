package com.topcom.cms.web.spring.controller;

import com.topcom.cms.base.model.BaseTreeEntityModel;
import com.topcom.cms.base.web.spring.controller.GenericTreeController;
import com.topcom.cms.domain.*;
import com.topcom.cms.service.ResourceManager;
import com.topcom.cms.service.SearchWordManager;
import com.topcom.cms.service.UserManager;
import com.topcom.cms.utils.FileUploader;
import com.topcom.cms.web.bind.annotation.CurrentUser;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 平台子系统与各级模块管理维护的交互控制
 *
 * @author lsm
 */
@Controller
@RequestMapping("/admin/resource/")
public class ResourceController extends
        GenericTreeController<Resource, Long, ResourceManager> {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger
            .getLogger(ResourceController.class);

    @Autowired
    FileUploader fileUploader;
    @Autowired
    UserManager userManager;
    @Autowired
    SearchWordManager searchWordManager;

    ResourceManager resourceManager;

    @Autowired
    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
        this.manager = this.resourceManager;
        this.treeManager = this.resourceManager;
    }

    /*
     * 节点拖动，改变节点的顺序，位置
     */
    @RequestMapping(value = "/changeChildId.json", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public void changeChildId(@RequestParam Long id,
                              @RequestParam Long parentId,
                              @RequestParam(required = false) Long nextChildId)
            throws IOException {
        this.resourceManager.changeChildId(id, parentId, nextChildId);

    }


    /*
     * 保存添加节点
     *
     */
    @RequestMapping(value = "/getLeafs", method = RequestMethod.GET)
    @ResponseBody
    public List<Resource> getLeafs() {

        return this.manager.getLeafs();
    }

    /**
     * 根据id，查找module对象，加入缓存机制
     *
     * @param id
     * @return Resource
     */
    @RequestMapping(value = "/getResoucesList/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Resource> getResoucesList(@PathVariable Long id,
                                          HttpServletRequest request, HttpServletResponse response) {
        List<Resource> list = this.resourceManager.getResoucesChildren(id);
        return list;
    }

    /**
     * 根据菜单id 获取单个菜单实体
     *
     * @param id //	 * @param request
     *           //	 * @param response
     * @return 菜单实体对象
     */
    @RequestMapping(value = "/getResoucesWithById/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Resource getResoucesWithById(@PathVariable Long id) {
        Resource resource = this.resourceManager.findResoucesWithoutById(id);
        return resource;
    }

    /**
     * 根据菜单id 获取单个菜单实体
     *
     * @param id //	 * @param request
     *           //	 * @param response
     * @return 父菜单id
     */
    @RequestMapping(value = "/getParentId/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Long getParentById(@PathVariable Long id) {
        Long parentId = this.resourceManager.getParentIdById(id);
        return parentId;
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
        Resource resource = this.manager.findByName(name);
        if (resource != null) {
            return false;
        }
        return true;
    }

    @Override
    @ResponseBody
    @RequestMapping(
            value = {"/getTree.json"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    public List<Resource> getTree(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id", required = false) Long id) {
        List<Resource> result = null;
        if(id == null) {
            result = this.treeManager.getRoot();
        } else {
            BaseTreeEntityModel node = (BaseTreeEntityModel)this.treeManager.findById(id);
            result = node.getChildren();
        }
        if (result!=null&&result.size()>0){
            for (Resource resource:result){
                resource.sortByChildId();
            }
        }
        return result;
    }


    @ApiOperation("获取resource")
    @RequestMapping(
            value = {"findByUser"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public Page<Resource> resource(@CurrentUser User user,@RequestParam(required = false) String word,@RequestParam(required = false) Integer limit,@RequestParam(required = false) Integer page) {
        User user1 = this.userManager.findById(user.getId());//缓存user懒加载，没有resource，需要在数据库查询
        //user.getPermissionNames();
        Set<Resource> resourceSet = user1.getResource();
        Set<Group> groups = user1.getGroups();
        if (resourceSet == null || resourceSet.size() == 0) {
            return null;
        }
        for (Resource resource : resourceSet) {
            resource.sortByChildId();
        }
        //增加查询条数
        searchWordManager.addClickCount(groups,word,1);
        return this.resourceManager.searchResource(resourceSet,word,limit,page);
    }

    @RequestMapping(value = "uploadImages/{name}", method = RequestMethod.POST)
    @ResponseBody
    public List<String> uploadImages(
            @RequestParam(value = "images") MultipartFile[] images,
            @PathVariable String name) throws Exception {

        List<UploadFile> uploadFiles = fileUploader.upload(images, "/home/topcom/resource/" + File.separatorChar + name);
        List<String> filepathList = uploadFiles.stream().map(uploadFile -> {
            return uploadFile.getFilepath();
        }).collect(Collectors.toList());
        return filepathList;
    }
}
