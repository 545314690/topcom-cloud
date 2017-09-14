package com.topcom.cms.yuqing.task.warning;

import com.topcom.cms.yuqing.domain.SubscriptionFollower;
import com.topcom.cms.yuqing.service.SubscriptionFollowerManager;
import com.topcom.cms.yuqing.utils.WechatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 刷新关注用户列表
 * 每天获取用户列表的接口访问次数为500
 * 每天获取用户详细信息的接口限制为500000
 * 每1（x点55分，因为预警都是整点）个小时获取列表并访问详细信息，那么支持500000/24=20000个用户的维护
 * Created by topcom on 2017/8/17 0017.
 */
@Component
public class ScanWechatJob {

    @Autowired
    SubscriptionFollowerManager subscriptionFollowerManager;

    @Autowired
    WechatUtil wechatUtil;

    List<SubscriptionFollower> allFollower = new ArrayList<>();

    @Scheduled(cron="0 55 0/1 * * ? ")   //一小时执行一次
    public void execute() {
        List<String> userList = wechatUtil.getUserList();
        List<SubscriptionFollower> removeUserList=getRemoveUsers(userList);
        subscriptionFollowerManager.deleteInBatch(removeUserList);
        updateFollowerinfo(userList);
    }

    private boolean updateFollowerinfo(List<String> userList) {
        if (userList==null||userList.size()==0){
            return false;
        }
        Map<String,Long> openidAndId = new HashMap<>();
        for (int i=0;i<allFollower.size();i++){
            openidAndId.put(allFollower.get(i).getOpenid(),allFollower.get(i).getId());
        }
        for (int i=0;i<userList.size();i++){
            String openid = userList.get(i);
            SubscriptionFollower userInfo = wechatUtil.getUserInfo(openid);
            if (userInfo==null){
                continue;
            }
            if (openidAndId.keySet().contains(openid)){
                userInfo.setId(openidAndId.get(openid));
                subscriptionFollowerManager.delete(userInfo.getId());
            }
            subscriptionFollowerManager.save(userInfo);
        }
        return true;
    }


    /**
     * 获取取关的用户列表
     * @param userList
     * @return
     */
    private List<SubscriptionFollower> getRemoveUsers(List<String> userList) {
        List<SubscriptionFollower> removeUsers = new ArrayList<>();
        allFollower = subscriptionFollowerManager.findAll();
        Set userSet = new HashSet<>();
        userSet.addAll(userList);
        if (userList==null||userList.size()==0){
            return null;
        }
        for (int i=0;i<allFollower.size();i++){
            String openid = allFollower.get(i).getOpenid();
            if (!userSet.contains(openid)){
                removeUsers.add(allFollower.get(i));
            }
        }
        return removeUsers;
    }
}
