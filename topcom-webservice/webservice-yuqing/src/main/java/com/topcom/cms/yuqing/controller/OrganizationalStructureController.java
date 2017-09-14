package com.topcom.cms.yuqing.controller;

import com.topcom.cms.base.model.BaseTreeEntityModel;
import com.topcom.cms.base.web.spring.controller.GenericTreeController;
import com.topcom.cms.common.page.PageRequest;
import com.topcom.cms.domain.User;
import com.topcom.cms.web.bind.annotation.CurrentUser;
import com.topcom.cms.yuqing.domain.Library;
import com.topcom.cms.yuqing.domain.OrganizationalStructure;
import com.topcom.cms.yuqing.domain.Staff;
import com.topcom.cms.yuqing.service.LibraryManager;
import com.topcom.cms.yuqing.service.OrganizationalStructureManager;
import com.topcom.cms.yuqing.service.StaffManager;
import com.topcom.cms.yuqing.service.WordManager;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/organizationalStructure/")
@Api("组织机构")
public class OrganizationalStructureController extends
       GenericTreeController<OrganizationalStructure, Long, OrganizationalStructureManager>
{
    OrganizationalStructureManager organizationalStructureManager;

    @Autowired
    public void setOrganizationalStructureManagerManager(OrganizationalStructureManager organizationalStructureManager) {
        this.organizationalStructureManager = organizationalStructureManager;
        this.manager = organizationalStructureManager;
        this.treeManager = organizationalStructureManager;
    }

    @Autowired
    StaffManager staffManager;
    /**
     * 根据类型查询当前登录用户的词库
     *
     * @return 分页数据
     */
    @RequestMapping(value = "/findByUser", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Page<OrganizationalStructure> findByUserId(@CurrentUser User user, @RequestBody PageRequest pageRequest) {
        return organizationalStructureManager.findByUserId(pageRequest.pageable(), user.getId());
    }


    @RequestMapping(value = "/findByName/{name}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Page<OrganizationalStructure> findByName(@CurrentUser User user, @PathVariable String name, @RequestBody PageRequest pageRequest) {
        return organizationalStructureManager.findByName(pageRequest.pageable(),name,user.getId());
    }


    @ResponseBody
    @RequestMapping(
            value = {"/getFullTree.json"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    public List<OrganizationalStructure> getFullTree(HttpServletRequest request,
                                                 HttpServletResponse response, @RequestParam(value = "id", required = false) Long id) {
        List<OrganizationalStructure> result = null;
        if(id == null) {
            result = this.treeManager.getRoot();
        } else {
            BaseTreeEntityModel node = (BaseTreeEntityModel)this.treeManager.findById(id);
            result = node.getChildren();
        }

        for (int i=0;i<result.size();i++){
            result.set(i,handleOne(result.get(i)));
        }
        return result;
    }

    private OrganizationalStructure handleOne(OrganizationalStructure OS) {
        List<OrganizationalStructure> organizationList = OS.getChildren();
        if (organizationList!=null&&organizationList.size()>0){
            for (int i=0;i<organizationList.size();i++){
                organizationList.set(i,handleOne(organizationList.get(i)));
            }
        }else {
            if (OS.isLeaf()){
                Long id = OS.getId();
                List<Staff> byOrganizationalStructureId = staffManager.findByOrganizationalStructureId(id);
                List<OrganizationalStructure> childList = new ArrayList<>();
                for (Staff s:byOrganizationalStructureId){
                    childList.add(new OrganizationalStructure(s.getPosition()+":"+s.getName(),s.getId()));
                }
                OS.setChildren(childList);
            }
        }
        return OS;
    }
}
