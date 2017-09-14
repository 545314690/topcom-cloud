package com.topcom.cms.service.impl;

import java.util.*;

import com.topcom.cms.dao.UserDao;
import com.topcom.cms.domain.Resource;
import com.topcom.cms.service.ResourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topcom.cms.base.service.impl.GenericTreeManagerImpl;
import com.topcom.cms.dao.ResourceDao;

/**
 * 资源访问实现类
 * 
 * @author lism
 * 
 */
@Service(value = "resourceManager")
@Transactional
public class ResourceManagerImpl extends GenericTreeManagerImpl<Resource, Long>
		implements ResourceManager {

	ResourceDao resourceDao;

	@Autowired
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
		this.dao = this.resourceDao;
		this.treeDao = this.resourceDao;
	}

	UserDao userDao;
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<Resource> getLeafs() {
		return resourceDao.getLeafs();
	}

	@Override
//	@Cacheable(value = "moduleCache", key = "#id + 'getResoucesChildren'")
	// 将缓存保存进andCache，并使用参数中的id加上一个字符串(这里使用方法名称)作为缓存的key
	public List<Resource> getResoucesChildren(Long id) {
		List<Resource> list = super.getChildren(id);
		return list;
	}

	@Override
//	@Cacheable(value = "moduleCache", key = "#id + 'findMouleById'")
	public Resource findResoucesById(Long id) {
		return super.findById(id);
	}

	@Override
//	@CacheEvict(value = "moduleCache", allEntries = true)
	// 清除掉全部缓存
	public void deleteResouces(Long id) {
		super.delete(id);

	}

	@Override
//	@CacheEvict(value = "moduleCache", allEntries = true)
	public Resource saveResouces(Resource model) {
		return super.save(model);
	}

	@Override
	public List<Resource> findResoucesByRoleId(Long id) {
		return resourceDao.findResourcesByRoleId(id);
	}

	@Override
	public List<Resource> findResoucesByRoleIds(Long[] ids) {
		return resourceDao.findResourcesByRoleIds(ids);
	}

	@Override
	public Resource findResoucesWithoutById(Long id) {
		return resourceDao.findResourcesWithoutById(id);
	}

	@Override
	public Page<Resource> fuzzyQueryByName(Pageable pageable, String name) {
		return resourceDao.fuzzyQueryByName(pageable, name);
	}

	@Override
	public Long getParentIdById(Long id) {
		return resourceDao.getParentIdById(id);
	}

	@Override
	public boolean changeChildId(Long id, Long parentId, Long nextChildId) {
		boolean result = false;
		try {
			if(nextChildId != null){//从nextChildId开始childId都加1，该节点的childId设置为nextChildId，更新父菜单id
				Long startChildId = nextChildId;
				Long newChildId = nextChildId;
				resourceDao.childIdAddOne(parentId,startChildId);
				resourceDao.setChildId(id,parentId,newChildId);
			}else{//该节点变成childId最大
				Long newChildId = resourceDao.getMaxChildId(parentId);//获取最大childId
				if(newChildId == null){
					newChildId = 1L;
				}
				resourceDao.setChildId(id, parentId, newChildId);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Long getMaxChildId(Long parentId) {
		return resourceDao.getMaxChildId(parentId);
	}

	@Override
	public Resource findByName(String name) {
		return resourceDao.findByName(name) ;
	}

	@Override
	public List<Resource> getAllResources() {
		return this.getDescendants(null);
	}

	@Override
	public Resource save(Resource entity) {
		return super.save(entity);
	}

	@Override
	public List<Resource> save(Iterable<Resource> entities) {
		return super.save(entities);
	}

	@Override
	public void delete(Long id) {
		super.delete(id);
	}

	@Override
	public void deleteInBatch(Iterable<Resource> entities) {
		super.deleteInBatch(entities);
	}
}
