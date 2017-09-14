package com.topcom.cms.yuqing.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.cms.yuqing.domain.Collection;
import com.topcom.cms.yuqing.domain.MediaType;
import com.topcom.cms.yuqing.domain.SubscriptionFollower;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface SubscriptionFollowerManager extends GenericManager<SubscriptionFollower, Long> {

    Page<SubscriptionFollower> findByNickname(Pageable pageable, String nickname);
    List<SubscriptionFollower> findByOpenid(String nickname);
}
