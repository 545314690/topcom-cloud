package com.topcom.cms.yuqing.controller;

import com.topcom.cms.common.page.DateParam;
import com.topcom.cms.common.page.PageRequest;
import com.topcom.cms.common.utils.DateUtil;
import com.topcom.cms.data.domain.ESBaseDomain;
import com.topcom.cms.es.service.AllESDataService;
import com.topcom.cms.es.utils.MapUtils;
import com.topcom.cms.es.utils.RedisTemplateUtils;
import com.topcom.cms.es.vo.BoolQueryRequest;
import com.topcom.cms.es.vo.KV;
import com.topcom.cms.es.vo.Keyword;
import com.topcom.cms.mongo.base.BaseController;
import com.topcom.cms.mongo.domain.Accident;
import com.topcom.cms.mongo.service.AccidentService;
import com.topcom.cms.yuqing.domain.Staff;
import com.topcom.cms.yuqing.domain.StaffSentiment;
import com.topcom.cms.yuqing.service.StaffManager;
import com.topcom.cms.yuqing.service.StaffSentimentManager;
import com.topcom.cms.yuqing.vo.request.StaffSentimentRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by topcom on 2017/9/1 0001.
 */
@RestController
@RequestMapping(value = "/staffSentiment")
@Api(value = "领导舆情接口")
public class StaffSentimentController extends BaseController<StaffSentiment, String, StaffSentimentManager> {

    @Autowired
    StaffManager staffManager;

    @Autowired
    AllESDataService esManager;
    @Autowired
    RedisTemplateUtils redisTemplateUtils;

    private Long saveTime = 1200000L;
    StaffSentimentManager staffSentimentManager;

    @Autowired
    public void setStaffSentimentManager(StaffSentimentManager staffSentimentManager) {
        this.baseService = staffSentimentManager;
        this.staffSentimentManager = staffSentimentManager;
    }

    @ApiOperation("findByStaffId")
    @RequestMapping(
            value = {"/findByStaffId"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public StaffSentiment findByStaffId(HttpServletRequest request, HttpServletResponse response,
                                            @ApiParam("staffId") @RequestParam(required = true) Long staffId){
        return this.staffSentimentManager.findByStaffId(staffId);
    }

    @RequestMapping(
            value = {"/findByStaffName"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public List<StaffSentiment> findByStaffName(HttpServletRequest request, HttpServletResponse response,
                                                @ApiParam("StaffName") @RequestParam(required = true) String name){
        return this.staffSentimentManager.findByStaffNameLike(name);
    }
    /**
     * 根据部门ID获取领导舆情列表
     * 缓存规则  根据部门id查询时，先查询redis有没有此id的数据，若有则查询mongo，
     * 若没有则查询es并存储到mongo中并在redis中做标记。redis缓存时间决定mongo中数据的有效期。
     * @return
     */
    @RequestMapping(value = "/getStaffSentimentByOTSId/", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Page<StaffSentiment> getStaffSentimentByOTSId(@RequestBody StaffSentimentRequest request){
        Object o = redisTemplateUtils.getValue(request.toString());
        List<Long> staffIdList = new ArrayList<>();
        List<Staff> staffList = staffManager.findByOrganizationalStructureId(request.getOrganizationalStructureId());
        if (staffList==null||staffList.size()<1){
            return null;
        }
        for (int i=0;i<staffList.size();i++){
            staffIdList.add(staffList.get(i).getId());
        }
        List<StaffSentiment> staffSentimentList = new ArrayList<>();
        if (o==null){
            for (int i=0; i<staffIdList.size();i++){
                Staff staff = staffManager.findById(staffIdList.get(i));
                staffSentimentList.add(searchOneStaff(staff,request));
            }
            redisTemplateUtils.saveValue(request.toString(),true,saveTime);
            staffSentimentManager.save(staffSentimentList);
        }
        return this.staffSentimentManager.findByIdIn(request.getPage().pageable(),staffIdList);
    }

    //后期改用多线程查询，临时单线程查询
    private StaffSentiment searchOneStaff(Staff staff, StaffSentimentRequest request) {
        StaffSentiment staffSentiment = new StaffSentiment();
        staffSentiment.setId(String.valueOf(staff.getId()));
        staffSentiment.setStaff(staff.toMap());
        BoolQueryRequest boolRequest = new BoolQueryRequest();
        boolRequest.setDate(request.getDate());
        boolRequest.setGroupName("nlp.sentiment.label");
        boolRequest.setKeyword(new Keyword(staff.getExpression()));
        PageRequest page = new PageRequest(1,10);
        page.setOrders(request.getSortBy());
        boolRequest.setPage(page);
        boolRequest.setFiled(request.getFiled());
        boolRequest.setType(request.getArticleType());
        //情感值
        Map kvMap =KV.kvList2Map(esManager.filterAndGroupBy(boolRequest));
        staffSentiment.setNEG((Long) (kvMap.get("NEG")==null?0:kvMap.get("NEG")));
        staffSentiment.setNEU((Long) (kvMap.get("NEU")==null?0:kvMap.get("NEU")));
        staffSentiment.setPOS((Long) (kvMap.get("POS")==null?0:kvMap.get("POS")));
        //总数 以及条新闻，最新？还是最负面？
        Page articlePage = esManager.findByMustShouldDateInType(boolRequest);
        if (articlePage.getTotalElements()>0){
            staffSentiment.setArticle((Map) articlePage.getContent().get(0));
            staffSentiment.setTotal(articlePage.getTotalElements());
            Long time = Long.valueOf(((Map) articlePage.getContent().get(0)).get("pubTime").toString());
            staffSentiment.setNewsDate(new Date(time));
        }

        return staffSentiment;

    }


}
