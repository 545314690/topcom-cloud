package com.topcom.cms.yuqing.controller;

import com.mongodb.DBObject;
import com.topcom.cms.common.page.PageRequest;
import com.topcom.cms.domain.User;
import com.topcom.cms.mongo.base.BaseController;
import com.topcom.cms.web.bind.annotation.CurrentUser;
import com.topcom.cms.yuqing.domain.Briefing;
import com.topcom.cms.yuqing.service.BriefingManager;
import com.topcom.cms.yuqing.vo.request.BriefingRequest;
import com.topcom.cms.yuqing.vo.request.SpecialBriefingRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/briefing")
@Api("舆情报告接口")
public class BriefingController extends BaseController<Briefing, String, BriefingManager> {

    BriefingManager briefingManager;

    @Autowired
    public void setBriefingManager(BriefingManager briefingManager) {
        this.briefingManager = briefingManager;
        this.baseService = briefingManager;
    }

    @ApiOperation("根据id查询详情")
    @RequestMapping(
            value = {"detail/{id}"},
            method = {RequestMethod.GET}
    )
    public DBObject findDetailById(@PathVariable String id) {
        return this.briefingManager.findDetailById(id);
    }

    /**
     * 根据简报标题、创建时间查询当前登录用户的简报
     *
     * @return 分页数据
     */
    @ApiOperation("根据创建时间、标题分页查询")
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Page<Briefing> search(@CurrentUser User user,@RequestBody BriefingRequest briefingRequest) {
        if (briefingRequest.getTitle() == null) {
            briefingRequest.setTitle("");
        }
        return briefingManager.findByTypeAndTitleLikeAndDateCreatedBetweenAndUserId(
                briefingRequest.getPage().pageable(), briefingRequest.getType(),
                briefingRequest.getTitle(), briefingRequest.getDate().startDate(), briefingRequest.getDate().endDate(),user.getId()
        );
    }

    /**
     * 根据简报标题、创建时间查询当前登录用户的简报
     *
     * @return 分页数据
     */
    @ApiOperation("根据创建时间、标题分页查询专报，如果有专题id,则返回该专题的报告")
    @RequestMapping(value = "/searchSpecial", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Page<Briefing> searchSpecial(@CurrentUser User user, @RequestBody SpecialBriefingRequest briefingRequest) {
        if (briefingRequest.getType() != Briefing.BriefingType.SPECIAL) {
            return null;
        }
        if (briefingRequest.getTitle() == null) {
            briefingRequest.setTitle("");
        }
        Long userId = user.getId();
        if (briefingRequest.getSubjectId() != null) {
            return briefingManager.findByTypeAndTitleLikeAndDateCreatedBetweenAndSubjectIdAndUserId(
                    briefingRequest.getPage().pageable(), briefingRequest.getType(),
                    briefingRequest.getTitle(), briefingRequest.getDate().startDate(),
                    briefingRequest.getDate().endDate(), briefingRequest.getSubjectId(),userId
            );
        } else {
            return briefingManager.findByTypeAndTitleLikeAndDateCreatedBetweenAndUserId(
                    briefingRequest.getPage().pageable(), briefingRequest.getType(),
                    briefingRequest.getTitle(), briefingRequest.getDate().startDate(), briefingRequest.getDate().endDate(),userId
            );
        }

    }

    /**
     * 根据简报标题、创建时间查询当前登录用户的简报
     *
     * @return 分页数据
     */
    @RequestMapping(value = "/searchByUser", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Page<Briefing> searchByUser(@CurrentUser User user, @RequestBody BriefingRequest briefingRequest) {
        if (briefingRequest.getTitle() == null) {
            briefingRequest.setTitle("");
        }
        return briefingManager.findByUserIdAndTypeAndTitleLikeAndDateCreatedBetween(briefingRequest.getPage().pageable(), user.getId(), briefingRequest.getType(), briefingRequest.getTitle(), briefingRequest.getDate().startDate(), briefingRequest.getDate().endDate());
    }

    /**
     * 根据专题id查询
     *
     * @return
     */
    @ApiOperation("根据专题id查询")
    @RequestMapping(value = "/subject/list/{subjectId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Briefing> findBySubjectId(@PathVariable Long subjectId) {
        return briefingManager.findBySubjectId(subjectId);
    }

    /**
     * 根据专题id分页查询
     *
     * @return
     */
    @ApiOperation("根据专题id分页查询")
    @RequestMapping(value = "/subject/page/{subjectId}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Page<Briefing> findPageBySubjectId(@PathVariable Long subjectId, @RequestBody PageRequest pageRequest) {
        return briefingManager.findBySubjectId(subjectId, pageRequest.pageable());
    }


    @ApiOperation("根据type查询详情")
    @RequestMapping(value = "detail/type", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object findDetailByType(HttpServletRequest request, HttpServletResponse response,
                                   @ApiParam("type") @RequestParam(required = false) String type) {
        return this.briefingManager.findDetailByType(type);
    }


}
