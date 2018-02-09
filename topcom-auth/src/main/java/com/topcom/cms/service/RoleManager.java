package com.topcom.cms.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.cms.domain.Role;
import org.springframework.data.jpa.repository.Query;

/**
 * 角色管理——服务接口
 * 
 * @author lism
 * 
 */
public interface RoleManager extends GenericManager<Role, Long> {

	/**
	 * 获取角色列表信息，重写父类 findAll 方法 去掉关联查询
	 * 
	 * @param page
	 *            分页信息
	 * 
	 * @return 分页列表信息
	 */
	Page<Role> findAllRole(Pageable page);

	/**
	 * 保存对象
	 * 
	 * @param role
	 * @return 角色对象
	 */
	Role saveRole(Role role);

	/**
	 * 通过ID删除相应角色
	 * 
	 * @param id
	 */
	void deleteRole(Long id);

	/**
	 * 通过用户ID 获取该用户拥有的角色集合
	 * 
	 * @param id
	 *            用户ID
	 * 
	 * @return 角色集合
	 */
	List<Role> findRolesByUserId(Long id);

	/**
	 * 根据用户组ID集合查找用户组的角色信息集合
	 * 
	 * @param ids
	 * @return 角色集合
	 */
	List<Role> findRolesByGroupIds(Long[] ids);

	/**
	 * 根据用户ID查找用户角色信息集合
	 * 
	 * @param id
	 * 
	 * @return 角色集合
	 */
	List<Role> findRolesByGroupId(Long id);

	/**
	 * 获取所有可用(未被禁用)的角色列表信息
	 * 
	 * @return 角色集合
	 */
	List<Role> findAllUsableRoles();

	/**
	 * 通过roleName模糊查询
	 * @param pageable
	 * @param roleName
	 * @return
	 */
	Page<Role> findByNameLike(Pageable pageable, String roleName);

	/**
	 * 根据角色名查询角色
	 * @param name
	 * @return
	 */
	Role findByName(String name);

}
