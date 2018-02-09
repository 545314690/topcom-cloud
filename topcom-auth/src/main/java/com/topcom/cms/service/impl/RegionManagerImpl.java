package com.topcom.cms.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.cms.dao.RegionDao;
import com.topcom.cms.domain.Region;
import com.topcom.cms.service.RegionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 区域信息访问实现类
 *
 * @author lism
 */
@Service(value = "regionManager")
@Transactional
public class RegionManagerImpl extends
        GenericManagerImpl<Region, Long> implements
        RegionManager {

    private RegionDao regionDao;

    @Autowired
    public void setUserDao(RegionDao regionDao) {
        this.regionDao = regionDao;
        this.dao = this.regionDao;
    }

    @Override
    public List<Region> findByLevelAndParentId(Region.Level level, Integer parentId, Sort sort) {
        return regionDao.findByLevelAndParentId(level, parentId,sort);
    }
}
