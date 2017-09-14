package com.topcom.cms.yuqing.service.impl;

import com.topcom.cms.mongo.base.BaseServiceImpl;
import com.topcom.cms.yuqing.dao.StaffSentimentDao;
import com.topcom.cms.yuqing.domain.StaffSentiment;
import com.topcom.cms.yuqing.service.StaffSentimentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by topcom on 2017/9/1 0001.
 */
@Service
public class StaffSentimentManagerImpl extends BaseServiceImpl<StaffSentiment, String> implements StaffSentimentManager {

    StaffSentimentDao staffSentimentDao;

    @Autowired
    public void setStaffSentmentDao(StaffSentimentDao staffSentimentDao) {
        this.staffSentimentDao = staffSentimentDao;
        this.baseDao = staffSentimentDao;
    }

    @Override
    public Page<StaffSentiment> findByIdIn(Pageable pageable, List<Long> staffIdList) {
        List<String> ids = staffIdList.stream().map(l -> String.valueOf(l)).collect(Collectors.toList());
        return staffSentimentDao.findByIdIn(pageable,ids);
    }

    @Override
    public StaffSentiment findByStaffId(Long id) {
        return this.staffSentimentDao.findByStaffId(id);
    }

    @Override
    public List<StaffSentiment> findByStaffNameLike(String name) {
        return this.staffSentimentDao.findByStaffNameLike("%"+name+"%");
    }

}
