package com.topcom.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.cms.dao.RoleDao;
import com.topcom.cms.domain.Role;
import com.topcom.cms.service.RoleManager;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色信息访问接口
 * 
 * @author lism
 * 
 */
@Service(value = "roleManager")
@Transactional
public class RoleManagerImpl extends GenericManagerImpl<Role, Long> implements
		RoleManager {

	RoleDao roleDao;

	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
		this.dao = this.roleDao;
	}

	@Override
	@Cacheable(value = "roleCache", key = "#page + 'findAll'")
	// 将缓存保存进andCache，并使用参数中的id加上一个字符串(这里使用方法名称)作为缓存的key
	public Page<Role> findAllRole(Pageable page) {
		Page<Role> result = this.roleDao.findAll(page);
		return result;
	}

	@Override
	@CacheEvict(value = "roleCache", allEntries = true)
	public Role saveRole(Role role) {
		Role result = this.dao.save(role);
		return result;
	}

	@Override
	@CacheEvict(value = "roleCache", allEntries = true)
	public void deleteRole(Long id) {
		this.dao.delete(id);
	}

	/**
	 * 根据用户ID查找用户角色信息,不关联查询
	 * 
	 * @param id
	 * @return List<role>
	 * */
	@Override
	@Cacheable(value = "roleCache", key = "#id + 'findRolesByUserId'")
	public List<Role> findRolesByUserId(Long id) {
		return roleDao.findRolesByUserId(id);
	}

	/**
	 * 根据用户组ID集合查找用户组的角色信息集合,不关联查询
	 * 
	 * @param ids
	 * @return List<role>
	 * */
	@Override
	public List<Role> findRolesByGroupIds(Long[] ids) {
		return roleDao.findRolesByGroupIds(ids);
	}

	/**
	 * 根据用户组ID查找用户组的角色信息,不关联查询
	 * 
	 * @param id
	 * @return List<role>
	 * */
	@Override
	public List<Role> findRolesByGroupId(Long id) {
		return roleDao.findRolesByGroupId(id);
	}

	/**
	 * 查找所有可用的角色集合,不关联查询
	 * 
	 * @param
	 * @return List<role>
	 * */
	@Override
	public List<Role> findAllUsableRoles() {
		return roleDao.findAllUsableRoles();
	}

	@Override
	public Page<Role> findByNameLike(Pageable pageable, String name) {
		return roleDao.findByNameLike(pageable, name);
	}

	@Override
	public Role findByName(String name) {
		return roleDao.findByName(name);
	}

}
