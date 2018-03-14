package com.topcom.bi.controller;

import com.topcom.bi.domain.ItemType;
import com.topcom.bi.service.ItemTypeService;
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
@RequestMapping("itemType")
public class ItemTypeController extends BaseController<ItemType, String, ItemTypeService> {
    private ItemTypeService itemTypeService;

    @Autowired
    public void setItemTypeManager(ItemTypeService itemTypeService) {
        this.itemTypeService = itemTypeService;
        this.baseService = itemTypeService;
    }

    @ApiOperation("分页根据名字搜索")
    @RequestMapping(
            value = {"/searchByName"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    public org.springframework.data.domain.Page<ItemType> search( @RequestParam String name,
                                                                  @RequestParam Integer page,@RequestParam Integer limit) {
        return this.itemTypeService.findByNameLike(name, new PageRequest(page-1,limit));
    }
    @ApiOperation("类别列表")
    @RequestMapping(
            value = {"/list"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    public Iterable<ItemType> list() {
        return this.itemTypeService.findAll();
    }
}
