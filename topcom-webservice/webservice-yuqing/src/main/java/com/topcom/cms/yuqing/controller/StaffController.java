package com.topcom.cms.yuqing.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.common.page.PageRequest;
import com.topcom.cms.domain.User;
import com.topcom.cms.web.bind.annotation.CurrentUser;
import com.topcom.cms.yuqing.domain.*;
import com.topcom.cms.yuqing.service.LibraryManager;
import com.topcom.cms.yuqing.service.OrganizationalStructureManager;
import com.topcom.cms.yuqing.service.StaffManager;
import com.topcom.cms.yuqing.service.WordManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/staff/")
@Api("员工管理接口")
public class StaffController extends
        GenericController<Staff, Long, StaffManager> {
    StaffManager staffManager;

    @Autowired
    OrganizationalStructureManager organizationalStructureManager;

    @Autowired
    public void setStaffManager(StaffManager staffManager) {
        this.staffManager = staffManager;
        this.manager = staffManager;
    }




    @ApiOperation("添加staff到组织机构")
    @RequestMapping(
            value = {"addStaffToOS"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public List<Staff> addStaffToOS(HttpServletRequest request, HttpServletResponse response,
                                      @ApiParam("staffIds") @RequestParam(required = true) Long[] staffIds,
                                      @ApiParam("组织机构id") @RequestParam(required = true) Long[] organizationalStructureIds) {
        List<Staff> result = new ArrayList<>();
        if (staffIds.length>0&&organizationalStructureIds.length>0){
            for (int i=0;i<staffIds.length;i++){
                Long id = staffIds[i];
                Staff staff = staffManager.findById(id);
                for (int j=0;j<organizationalStructureIds.length;j++){
                    Long OSId = organizationalStructureIds[j];
                    OrganizationalStructure organizationalStructure = this.organizationalStructureManager.findById(OSId);
                    staff.addOrganizationalStructure(organizationalStructure);
                    staff.setId(Long.valueOf(id.toString()));
                    staff.setDateModified(new Date());
                }
                this.staffManager.save(staff);
                result.add(staff);
            }
        }
        return result;
    }

    @ApiOperation("删除staff到组织机构")
    @RequestMapping(
            value = {"removeOSFromStaff"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public List<Staff> removeOSFromStaff(HttpServletRequest request, HttpServletResponse response,
                                    @ApiParam("staffIds") @RequestParam(required = true) Long[] staffIds,
                                    @ApiParam("组织机构id") @RequestParam(required = true) Long[] organizationalStructureIds) {
        List<Staff> result = new ArrayList<>();
        if (staffIds.length>0&&organizationalStructureIds.length>0){
            for (int i=0;i<staffIds.length;i++){
                Long id = staffIds[i];
                Staff staff = staffManager.findById(id);
                for (int j=0;j<organizationalStructureIds.length;j++){
                    Long OSId = organizationalStructureIds[j];
                    OrganizationalStructure organizationalStructure = this.organizationalStructureManager.findById(OSId);
                    staff.removeOrganizationalStructure(organizationalStructure);
                    staff.setId(Long.valueOf(id.toString()));
                    staff.setDateModified(new Date());
                }
                this.staffManager.save(staff);
                result.add(staff);
            }
        }
        return result;
    }



    @RequestMapping(value = "/findByOrganizationalStructureName/{name}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object findByOrganizationalStructureName(@CurrentUser User user, @PathVariable String name, @RequestBody PageRequest pageRequest) {
        return this.staffManager.findByOrganizationalStructuresName(pageRequest.pageable(),name,user.getId());
    }


    @RequestMapping(value = "/findByStaffName/{name}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object findByName(@CurrentUser User user, @PathVariable String name, @RequestBody PageRequest pageRequest) {
        return this.staffManager.findByName(pageRequest.pageable(),name,user.getId());
    }

    /**
     * 根据用户查询员工，如果组织机构为空则返回此用户下全部员工
     * @param user
     * @param pageRequest
     * @param organizationalStructureId
     * @return
     */
    @RequestMapping(value = "/findByUserId/", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Page<Staff> findByUserId(@CurrentUser User user, @RequestBody PageRequest pageRequest,
                             @ApiParam("组织机构id") @RequestParam(required = false) Long organizationalStructureId) {
        if (organizationalStructureId==null){
            return this.staffManager.findByOrganizationalStructureUserId(pageRequest.pageable(),user.getId());
        }
        else return this.staffManager.findByOrganizationalStructureIdAndUserId(pageRequest.pageable(),organizationalStructureId,user.getId());
    }
    @RequestMapping(value = "/getKeyWordByOrganizationalStructure/", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Keywords getKeyWordByOrganizationalStructure(@CurrentUser User user, @RequestBody PageRequest pageRequest,
                                    @ApiParam("组织机构id") @RequestParam(required = false) Long organizationalStructureId) {
        Keywords keywords = new Keywords();
        List<Staff> staffList = new ArrayList<>();
        if (organizationalStructureId==null){
            staffList = this.staffManager.findByOrganizationalStructureUserId(pageRequest.pageable(),user.getId()).getContent();
        }
        else staffList =  this.staffManager.findByOrganizationalStructureIdAndUserId(pageRequest.pageable(),organizationalStructureId,user.getId()).getContent();
        String expretion = "";
        if (staffList.size()==0){
            return keywords;
        }else {
            expretion = "("+staffList.get(0).getExpression()+")";
            for (int i =1;i<staffList.size();i++){
                expretion = expretion+" OR ("+staffList.get(i).getExpression()+")";
            }
        }
        keywords.setExpression(expretion);
        return keywords;
    }



}
