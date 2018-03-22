package com.topcom.cms.service.impl;

import java.util.*;

import com.topcom.cms.dao.UserDao;
import com.topcom.cms.domain.Resource;
import com.topcom.cms.service.ResourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topcom.cms.base.service.impl.GenericTreeManagerImpl;
import com.topcom.cms.dao.ResourceDao;
import org.springframework.util.StringUtils;

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


	@Override
	public Page<Resource> searchResource(Set<Resource> resourceSet,String word, Integer limit, Integer page,String filterType) {
		if (limit==null||limit==0){
			limit=20;
		}
		if (page==null||page==0){
			page=1;
		}
		List<Resource> content = new ArrayList<>();
		if (StringUtils.isEmpty(word)){
			content.addAll(resourceSet);
		}else {
			content = searchText(word,resourceSet,filterType);
		}
		int index_s = (page-1)*limit;
		int index_e = page*limit;
		if (index_s>content.size()){
			content.clear();
		}else {
			content =content.subList(index_s,index_e>content.size()?content.size():index_e);
		}
		return new PageImpl(content,new PageRequest(page-1,limit),resourceSet.size());
	}

	private List<Resource> searchText(String keyWord,Set<Resource> resourceList,String filterType){
		Map<Resource,Double> resourceMap = new HashMap<>();
		if ("description".equals(filterType)){
			for(Resource resource:resourceList){
				Double d = 0D;
				String description = resource.getDescription();
				if (description!=null&&description.indexOf(keyWord)!=-1){
					d=d+1;
				}
				if (d>0){
					resourceMap.put(resource,d);
				}
			}
		}else if ("name".equals(filterType)){
			for(Resource resource:resourceList){
				Double d = 0D;
				String name = resource.getName();
				if (name!=null&&name.indexOf(keyWord)!=-1){
					d = d+10;
				}
				if (d>0){
					resourceMap.put(resource,d);
				}
			}
		}else {
			for(Resource resource:resourceList){
				Double d = 0D;
				String name = resource.getName();
				String description = resource.getDescription();
				if (name!=null&&name.indexOf(keyWord)!=-1){
					d = d+10;
				}
				if (description!=null&&description.indexOf(keyWord)!=-1){
					d=d+1;
				}
				if (d>0){
					resourceMap.put(resource,d);
				}
			}
		}
		return sort(resourceMap);
	}

	/**
	 * 排序
	 * @param coats
	 * @return
     */
	private List<Resource> sort(Map<Resource, Double> coats){
		List<Map.Entry<Resource, Double>> mappingList = null;
		mappingList = new ArrayList<Map.Entry<Resource, Double>>(coats.entrySet());
		Collections.sort(mappingList, new Comparator<Map.Entry<Resource, Double>>() {
			@Override
			public int compare(Map.Entry<Resource, Double> mapping1, Map.Entry<Resource, Double> mapping2) {
				return -mapping1.getValue().compareTo(mapping2.getValue());
			}
		});
		List<Resource> result = new ArrayList<>();
		for (int i=0;i<mappingList.size();i++){
			result.add(mappingList.get(i).getKey());
		}
		return result;
	}


	public static void main(String[] args) {
		List list= new ArrayList<>();
		for (int i=0;i<100;i++){
			list.add(i);
		}
		Page page = new PageImpl(list,new PageRequest(0,2),100);
		System.out.println(page.getTotalElements());
	}
}
