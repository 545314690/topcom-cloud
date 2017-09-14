package com.topcom.cms.yuqing.dao;

import com.topcom.cms.base.dao.GenericDao;
import com.topcom.cms.yuqing.domain.SubscriptionFollower;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscriptionFollowerDao extends GenericDao<SubscriptionFollower, Long> {

    Page<SubscriptionFollower> findByNickname(Pageable pageable, String nickname);

    List<SubscriptionFollower> findByOpenid(String nickname);

}
