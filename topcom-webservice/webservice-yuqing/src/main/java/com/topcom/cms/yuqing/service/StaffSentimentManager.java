package com.topcom.cms.yuqing.service;

import com.topcom.cms.mongo.base.BaseService;
import com.topcom.cms.mongo.domain.Accident;
import com.topcom.cms.yuqing.domain.StaffSentiment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by topcom on 2017/9/1 0001.
 */
public interface StaffSentimentManager extends BaseService<StaffSentiment, String> {
    Page<StaffSentiment> findByIdIn(Pageable pageable, List<Long> staffIdList);

    StaffSentiment findByStaffId(Long id);

    List<StaffSentiment> findByStaffNameLike(String name);
}
