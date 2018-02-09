package com.topcom.cms.dao;

import com.topcom.cms.base.dao.GenericDao;
import com.topcom.cms.domain.Region;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * 区域信息访问接口
 * 
 * @author lism
 * 
 */
public interface RegionDao extends GenericDao<Region, Long> {

    List<Region> findByLevelAndParentId(Region.Level level, Integer parentId, Sort sort);
}
