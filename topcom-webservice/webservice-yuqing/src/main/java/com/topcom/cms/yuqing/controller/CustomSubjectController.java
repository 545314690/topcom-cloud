package com.topcom.cms.yuqing.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.web.bind.annotation.CurrentUser;
import com.topcom.cms.yuqing.service.CustomSubjectManager;
import com.topcom.cms.yuqing.domain.CustomSubject;
import com.topcom.cms.domain.User;
import com.topcom.cms.yuqing.task.briefing.BriefingCreator;
import com.topcom.cms.yuqing.task.briefing.BriefingCreatorImpl;
import com.topcom.cms.yuqing.vo.request.CustomSubjectRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/customSubject/")
@Api("自定义专题接口")
public class CustomSubjectController extends
        GenericController<CustomSubject, Long, CustomSubjectManager> {
    CustomSubjectManager customSubjectManager;

    @Autowired
    BriefingCreator briefingCreator;

    @Autowired
    public void setCustomSubjectManager(
            CustomSubjectManager customSubjectManager) {
        this.customSubjectManager = customSubjectManager;
        this.manager = customSubjectManager;
    }


    /**
     * 分页查询
     *
     * @return 分页数据
     */
    @ApiOperation("分页查询")
    @RequestMapping(value = "/findByPage", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Page<CustomSubject> findByPage(@RequestBody com.topcom.cms.common.page.PageRequest pageRequest) {
        return customSubjectManager.findAll(pageRequest.pageable());
    }

//    /**
//     * 分页查询
//     *
//     * @return 分页数据
//     */
//    @ApiOperation("findByUserIdAndNameLike")
//    @RequestMapping(value = "/findByUserIdAndNameLike", method = RequestMethod.POST, produces = "application/json")
//    @ResponseBody
//    public Page<CustomSubject> findByUserIdAndNameLike(@CurrentUser User user,@RequestBody com.topcom.cms.common.page.PageRequest pageRequest,
//                                                 @ApiParam("专题名称") @RequestParam(required = false) String subjectName) {
//        return customSubjectManager.findByUserIdAndNameLike(pageRequest.pageable(),user.getId(),subjectName);
//    }

    /**
     * 查询当前登录用户的自定义专题
     *
     * @return 分页数据
     */
    @RequestMapping(value = "/findByUser", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Page<CustomSubject> findByUserId(@CurrentUser User user, @RequestBody CustomSubjectRequest customSubjectRequest) {
        String subjectName = customSubjectRequest.getSubjectName();
        if (StringUtils.isEmpty(subjectName)){
            return this.manager.findByUserId(customSubjectRequest.getPage().pageable(), user.getId());
        }
        return customSubjectManager.findByUserIdAndNameLike(customSubjectRequest.getPage().pageable(),user.getId(),subjectName);    }

    /**
     * 需要覆写父类方法，有切面
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @Override
    public CustomSubject create(@RequestBody CustomSubject model) throws Exception {
        return super.create(model);
    }


    /**
     * 需要覆写父类方法，有切面
     *
     * @param model
     * @return
     */

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.PUT},
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    @ResponseBody
    public CustomSubject update(@PathVariable Long id, @RequestBody CustomSubject model) {
        model.setId(Long.valueOf(id.toString()));
        model.setDateModified(new Date());
        model = this.manager.save(model);
        return model;
    }

    @RequestMapping(
            value = {"/updateReport/{id}"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public ModelMap updateReport(@PathVariable String id) throws Exception {
        briefingCreator.updateSpecial(id);
        return new ModelMap("status", true);
    }

    @RequestMapping(value = {"/{id}"}, method = {RequestMethod.DELETE}, produces = {"application/json"}
    )
    @ResponseBody
    @Override
    public void delete(@PathVariable Long id) throws IOException {
        super.delete(id);
    }
}
