package com.topcom.cms.yuqing.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.cms.yuqing.dao.CollectionDao;
import com.topcom.cms.yuqing.dao.SubscriptionFollowerDao;
import com.topcom.cms.yuqing.domain.Collection;
import com.topcom.cms.yuqing.domain.MediaType;
import com.topcom.cms.yuqing.domain.SubscriptionFollower;
import com.topcom.cms.yuqing.service.CollectionManager;
import com.topcom.cms.yuqing.service.SubscriptionFollowerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service(value = "subscriptionFollowerManager")
@Transactional
public class SubscriptionFollowerManagerImpl extends GenericManagerImpl<SubscriptionFollower, Long>
        implements SubscriptionFollowerManager {

    SubscriptionFollowerDao subscriptionFollowerDao;

    @Autowired
    public void setCollectionDao(SubscriptionFollowerDao subscriptionFollowerDao) {
        this.subscriptionFollowerDao = subscriptionFollowerDao;
        this.dao = this.subscriptionFollowerDao;
    }

    @Override
    public Page<SubscriptionFollower> findByNickname(Pageable pageable,String nickname) {
        return subscriptionFollowerDao.findByNickname(pageable,nickname);
    }

    @Override
    public List<SubscriptionFollower> findByOpenid(String nickname) {
        return subscriptionFollowerDao.findByOpenid(nickname);
    }


    @Override
    public SubscriptionFollower save(SubscriptionFollower entity) {
        List<SubscriptionFollower> byOpenid = this.findByOpenid(entity.getOpenid());
        this.deleteInBatch(byOpenid);
        return super.save(entity);
    }

    @Override
    public List<SubscriptionFollower> save(Iterable<SubscriptionFollower> entities) {
        for (SubscriptionFollower entity:entities){
            List<SubscriptionFollower> byOpenid = this.findByOpenid(entity.getOpenid());
            this.deleteInBatch(byOpenid);
        }
        return super.save(entities);
    }
}
