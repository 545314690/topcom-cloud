package com.topcom.cms.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.cms.domain.Region;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * 区域信息访问接口
 *
 * @author lism
 */
public interface RegionManager extends
        GenericManager<Region, Long> {


    List<Region> findByLevelAndParentId(Region.Level level, Integer parentId, Sort sort);
}
