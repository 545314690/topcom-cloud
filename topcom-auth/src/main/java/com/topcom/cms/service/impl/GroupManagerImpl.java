package com.topcom.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topcom.cms.base.service.impl.GenericTreeManagerImpl;
import com.topcom.cms.dao.GroupDao;
import com.topcom.cms.domain.Group;
import com.topcom.cms.service.GroupManager;

/**
 * 用户组信息访问实现类
 * 
 * @author lism
 * 
 */
@Service(value = "groupManager")
@Transactional
public class GroupManagerImpl extends GenericTreeManagerImpl<Group, Long>
		implements GroupManager {

	GroupDao groupDao;

	@Autowired
	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
		this.dao = this.groupDao;
		this.treeDao = this.groupDao;
	}

	@Override
	@Cacheable(value = "groupCache", key = "#id + 'getGroupListById'")
	// 将缓存保存进groupCache，并使用参数中的id加上一个字符串(这里使用方法名称)作为缓存的key
	public Group findGroupById(Long id) {
		Group g = groupDao.findTreeGroupById(id);
		findAllChildren(g);
		return g;
	}

	private void findAllChildren(Group g) {
		List<Group> list = groupDao.findChildrenByParentId(g.getId());
		if (list != null && list.size() != 0) {
			g.setChildren(list);
			g.setLeaf(false);
		}
		for (Group group : list) {
			findAllChildren(group);
		}

	}

	@Override
	@CacheEvict(value = "groupCache", allEntries = true)
	// 清除掉全部缓存
	public void deleteGroup(Long id) {
		super.delete(id);

	}

	@Override
	@CacheEvict(value = "groupCache", allEntries = true)
	public Group saveGroup(Group model) {
		return super.save(model);
	}

	@Override
	public List<Group> findGroupsByUserId(Long id) {
		return groupDao.findGroupsByUserId(id);
	}

	@Override
	public Group findByName(String name) {
		return groupDao.findByName(name);
	}

	@Override
	public Long getParentIdById(Long id) {
		return groupDao.getParentIdById(id);
	}

	@Override
	public boolean changeChildId(Long id, Long parentId, Long nextChildId) {
		boolean result = false;
		try {
			if(nextChildId != null){//从nextChildId开始childId都加1，该节点的childId设置为nextChildId，更新父菜单id
				Long startChildId = nextChildId;
				Long newChildId = nextChildId;
				groupDao.childIdAddOne(parentId,startChildId);
				groupDao.setChildId(id,parentId,newChildId);
			}else{//该节点变成childId最大
				Long newChildId = groupDao.getMaxChildId(parentId);//获取最大childId
				if(newChildId == null){
					newChildId = 1L;
				}
				groupDao.setChildId(id, parentId, newChildId);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Group> findChildrenByParentId(Long id) {
		// TODO Auto-generated method stub
		List<Group> children = groupDao.findChildrenByParentId(id);
		return children;
	}

}
