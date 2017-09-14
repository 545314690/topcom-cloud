package com.topcom.cms.web.spring.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.topcom.cms.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcom.cms.base.web.spring.controller.GenericTreeController;
import com.topcom.cms.domain.Group;
import com.topcom.cms.service.GroupManager;

/**
 * 用户组（部门）控制器
 * 
 * @author lism
 *
 */
@Controller
@RequestMapping("/admin/group/")
public class GroupController extends GenericTreeController<Group, Long, GroupManager> {

	GroupManager groupManager;

	@Autowired
	public void setGroupManager(GroupManager groupManager) {
		this.groupManager = groupManager;
		this.manager = this.groupManager;
		this.treeManager = this.groupManager;
	}

	@RequestMapping(method = RequestMethod.GET, value = "index.html")
	public String index() {
		return "/manage/group/index";
	}

	@RequestMapping(method = RequestMethod.GET, value = "add.html")
	public String add() {
		return "/manage/group/add";
	}

	@RequestMapping(method = RequestMethod.GET, value = "edit.html")
	public String edit() {
		return "/manage/group/edit";
	}

	/**
	 * 删除控制
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public void delete(@PathVariable Long id) throws IOException {
		this.manager.deleteGroup(id);

	}

	/**
	 * 新增控制
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public Group create(@RequestBody Group model) {
		this.model = model;
		Date date = new Date();
		this.model.setDateCreated(date);
		this.model.setDateModified(date);
		this.model = this.manager.saveGroup(this.model);
		return this.model;
	}

	/*
	 * 更新树节点
	 * 
	 * @see
	 * edu.zut.cs.nlp.cms.web.spring.controller.GenericController#update(java
	 * .io.Serializable, edu.zut.cs.nlp.cms.model.BaseEntityModel)
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public Group update(@PathVariable Long id, @RequestBody Group model) {
		this.model = this.manager.saveGroup(model);
		return this.model;
	}

	/**
	 * 根据指定的id，获取实体对象 重写get方法，使菜单按照parent.id asc ,childId asc 的顺序排序
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Group get(@PathVariable Long id) {
		Group g = this.manager.findById(id);
//		g.sortByChildId();// 根据childId 升序排序
		return g;

	}

	/**
	 * 根据菜单id 获取单个菜单实体
	 * 
	 * @param id
	 * @return 父菜单id
	 */
	@RequestMapping(value = "/getParentId/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Long getParentById(@PathVariable Long id) {
		Long parentId = this.groupManager.getParentIdById(id);
		return parentId;
	}

	@RequestMapping(value = "/checkGroupName.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> checkModuleName(HttpServletRequest request) {
		Map<String, Boolean> ret = new HashMap<>();
		ret.put("success", true);
		String name = request.getParameter("groupName");
		Group m = this.manager.findByName(name);
		if (m != null) {
			ret.put("success", false);// 组已存在
		}
		return ret;
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
		Group group = this.manager.findByName(name);
		if (group != null) {
			return false;
		}
		return true;
	}
	/*
	 * 节点拖动，改变节点的顺序，位置
	 */
	@RequestMapping(value = "/changChildId.json", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void changeChildId(@RequestParam Long id, @RequestParam Long parentId,
			@RequestParam(required = false) Long nextChildId) throws IOException {
		this.manager.changeChildId(id, parentId, nextChildId);
	}
	
	/**
	 * 得到树结构;
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getGroup.json", method = RequestMethod.GET, produces = "application/json")
	public List<Group> getChildren(@RequestParam(required = false) Long id) {
		//List<Group> result = this.treeManager.getChildren(id);
		List<Group> result;
		if(id!=null){
			result = groupManager.findChildrenByParentId(id);
		}else{
			result = groupManager.findAll();
		}
			
		return result;
	}

}
