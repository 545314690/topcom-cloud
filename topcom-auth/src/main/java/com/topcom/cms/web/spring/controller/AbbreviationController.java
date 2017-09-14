package com.topcom.cms.web.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcom.cms.base.web.spring.controller.GenericTreeController;
import com.topcom.cms.domain.Abbreviation;
import com.topcom.cms.domain.Unit;
import com.topcom.cms.service.AbbreviationManager;

/**
 * 平台管理-简称信息管理维护的交互控制
 * 
 * @author maodaqiang
 * 
 */
@Controller
@RequestMapping("/abbreviation/")
public class AbbreviationController extends
		GenericTreeController<Abbreviation, Long, AbbreviationManager> {

	AbbreviationManager abbreviationManager;

	@Autowired
	public void setAbbreviationManager(AbbreviationManager abbreviationManager) {
		this.abbreviationManager = abbreviationManager;
		this.manager = this.abbreviationManager;
		this.treeManager = this.abbreviationManager;
	}

	@RequestMapping(method = RequestMethod.GET, value = "index.html")
	public String index() {
		return "/admin/abbreviation/index";
	}

	/**
	 * 根据输入的实体对象，创建一个新的实体对象
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public Abbreviation create(@RequestBody Abbreviation model) {
		this.model = this.manager.save(model);
		return this.model;
	}

	/**
	 * 通过ID 获取其所有子节点集合
	 * 
	 * @param id
	 * @return 子节点集合
	 */
	@ResponseBody
	@RequestMapping(value = "/getTree/{id}", method = RequestMethod.GET, produces = "application/json")
	public List<Abbreviation> getTreeChildren(@PathVariable Long id) {
		List<Abbreviation> result = this.treeManager.getChildren(id);
		return result;
	}

	/**
	 * 根据输入的实体对象信息，修改指定id的实体对象
	 * 
	 * @param id
	 * @param model
	 * @return 实体对象
	 */
	@RequestMapping(value = "/getTree/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public Abbreviation update(@PathVariable int id,
			@RequestBody Abbreviation model) {
		Abbreviation abb = this.manager.findById(Long.valueOf(id));
		Unit unit = abb.getUnit();
		abb.setShortName(model.getShortName());
		unit.setUnitName(model.getUnit().getUnitName());
		abb.setFlag(model.getFlag());
		abb.setUnit(unit);
		this.model = this.manager.save(abb);
		return this.model;
	}

}
