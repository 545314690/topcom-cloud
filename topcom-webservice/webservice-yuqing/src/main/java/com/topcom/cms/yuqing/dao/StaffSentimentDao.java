package com.topcom.cms.yuqing.dao;

import com.topcom.cms.mongo.base.BaseDao;
import com.topcom.cms.mongo.domain.Accident;
import com.topcom.cms.yuqing.domain.StaffSentiment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by topcom on 2017/9/1 0001.
 */
@Repository
public interface StaffSentimentDao extends BaseDao<StaffSentiment, String> {
    Page<StaffSentiment> findByIdIn(Pageable pageable, List<String> staffIdList);

    StaffSentiment findByStaffId(Long id);

    List<StaffSentiment> findByStaffNameLike(String s);
}
