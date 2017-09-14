package com.topcom.cms.service;

import java.util.List;

import com.topcom.cms.base.service.GenericTreeManager;
import com.topcom.cms.domain.Group;

/**
 * 用户组信息访问接口
 * 
 * @author maodaqiang
 * 
 */
public interface GroupManager extends GenericTreeManager<Group, Long> {

	/**
	 * 通过id 获取该用户组
	 * 
	 * @param id
	 * @return 用户组对象
	 */
	Group findGroupById(Long id);

	/**
	 * 通过id 删除该用户组
	 * 
	 * @param id
	 */
	void deleteGroup(Long id);

	/**
	 * 保存用户组信息
	 * 
	 * @param group
	 *            用户组对象
	 * @return 用户组对象
	 */
	Group saveGroup(Group group);

	/**
	 * 根据用户id 获取该用户组的所有子组
	 * 
	 * @param id
	 * @return 用户组集合
	 */
	List<Group> findGroupsByUserId(Long id);
	
	/**
	 * 根据用户组id获取该组所有的子组，不包括角色数据
	 * @param 用户组id
	 * @return
	 */
	
	List<Group> findChildrenByParentId(Long id);

	Group findByName(String name);

	Long getParentIdById(Long id);

	boolean changeChildId(Long id, Long parentId, Long nextChildId);

}
