package com.topcom.cms.web.spring.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.domain.Region;
import com.topcom.cms.perm.annotation.PublicResource;
import com.topcom.cms.service.RegionManager;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 区域维护的交互控制
 *
 * @author lism
 */
@Controller
@RequestMapping("/region/")
public class RegionController extends
        GenericController<Region, Long, RegionManager> {

    private RegionManager regionManager;

    @Autowired
    public void setRegionManager(RegionManager regionManager) {
        this.regionManager = regionManager;
        this.manager = this.regionManager;
    }

    @ResponseBody
    @RequestMapping(
            value = {"/list/"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @PublicResource
    public List<Region> list(@RequestParam Region.Level level, @RequestParam Integer parentId) {

        Sort sort = new Sort(Sort.Direction.ASC, "sort");
        return regionManager.findByLevelAndParentId(level, parentId,sort);
    }
}
