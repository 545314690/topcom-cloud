package com.topcom.bi.controller;

import com.topcom.bi.domain.Interface;
import com.topcom.bi.service.InterfaceService;
import com.topcom.cms.mongo.base.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lism on 17-10-31.
 *
 * @author lism
 */
@RestController
@RequestMapping("interface")
public class InterfaceController extends BaseController<Interface, String, InterfaceService> {
    private InterfaceService interfaceService;

    @Autowired
    public void setInterfaceManager(InterfaceService interfaceService) {
        this.interfaceService = interfaceService;
        this.baseService = interfaceService;
    }

    @ApiOperation("分页根据名字搜索")
    @RequestMapping(
            value = {"/searchByName"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    public org.springframework.data.domain.Page<Interface> search( @RequestParam String name,
                                                                  @RequestParam Integer page,@RequestParam Integer limit) {
        return this.interfaceService.findByNameLike(name, new PageRequest(page-1,limit));
    }

}
