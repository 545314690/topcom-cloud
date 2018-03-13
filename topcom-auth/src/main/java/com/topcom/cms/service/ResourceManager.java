package com.topcom.cms.service;

import java.util.List;
import java.util.Set;

import com.topcom.cms.domain.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.topcom.cms.base.service.GenericTreeManager;

/**
 * 菜单信息访问接口
 * 
 * @author lism
 * 
 */
public interface ResourceManager extends GenericTreeManager<Resource, Long> {

	/**
	 * 获取所有叶子节点的菜单
	 * 
	 * @return 列表集合
	 */
	List<Resource> getLeafs();

	/**
	 * 
	 * @param id
	 *            菜单ID
	 * @return
	 */
	List<Resource> getResoucesChildren(Long id);

	/**
	 * 通过id 获取菜单
	 * 
	 * @param id
	 * @return 菜单对象
	 */
	Resource findResoucesById(Long id);

	/**
	 * 通过id 删除菜单
	 * 
	 * @param id
	 */
	void deleteResouces(Long id);

	/**
	 * 保存菜单对象
	 * 
	 * @param model
	 * @return 菜单对象
	 */
	Resource saveResouces(Resource model);

	/**
	 * 通过角色ID 获取该角色下所有的菜单集合
	 * 
	 * @param id
	 * @return 菜单（权限）对象集合
	 */
	List<Resource> findResoucesByRoleId(Long id);

	/**
	 * 根据角色IDS集合查找用户组的权限(菜单)信息集合
	 * 
	 * @param ids
	 * @return 菜单（权限）对象集合
	 */
	List<Resource> findResoucesByRoleIds(Long[] ids);

	/**
	 * 根据菜单id 获取菜单id
	 * 
	 * @param id
	 * @return 菜单对象
	 */
	Resource findResoucesWithoutById(Long id);

	/**
	 * 根据name模糊查询
	 * @param pageable
	 * @param name
	 * @return 分页数据
	 */
	Page<Resource> fuzzyQueryByName(Pageable pageable, String name);

	/**
	 * 通过id获取父菜单id
	 * @param id
	 * @return
	 */
	Long getParentIdById(Long id);

	/**
	 * 改变菜单childId
	 * @param id
	 * @param parentId
	 * @param nextChildId
	 * @return
	 */
	boolean changeChildId(Long id, Long parentId, Long nextChildId);

	/**
	 * 获取parentId下子菜单的childId最大值
	 * @param parentId
	 * @return
	 */
	Long getMaxChildId(Long parentId);

	/**
	 * 根据菜单名获得菜单
	 * @param name
	 * @return
	 */
	Resource findByName(String name);


//	Set<String> getUserPermissions(Long id);
	List<Resource> getAllResources();


	Page<Resource> searchResource(Set<Resource> resourceSet, String word,Integer limit, Integer page);
}
