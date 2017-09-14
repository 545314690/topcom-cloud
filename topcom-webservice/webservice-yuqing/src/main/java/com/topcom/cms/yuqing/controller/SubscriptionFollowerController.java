package com.topcom.cms.yuqing.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.domain.User;
import com.topcom.cms.web.bind.annotation.CurrentUser;
import com.topcom.cms.yuqing.domain.Collection;
import com.topcom.cms.yuqing.domain.MediaType;
import com.topcom.cms.yuqing.domain.SubscriptionFollower;
import com.topcom.cms.yuqing.service.CollectionManager;
import com.topcom.cms.yuqing.service.SubscriptionFollowerManager;
import com.topcom.cms.yuqing.utils.WechatUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/subscriptionFollower/")
public class SubscriptionFollowerController extends
        GenericController<SubscriptionFollower, Long, SubscriptionFollowerManager> {

    @Autowired
    WechatUtil wechatUtil;
    SubscriptionFollowerManager subscriptionFollowerManager;
    //private final int TIMEOUT=10;
    Pageable DEFAL_PAGEABLE=new PageRequest(0,10);

    @Autowired
    public void setSubscriptionFollowerManager(SubscriptionFollowerManager subscriptionFollowerManager) {
        this.subscriptionFollowerManager = subscriptionFollowerManager;
        this.manager = subscriptionFollowerManager;
    }



    @ApiOperation("是否存在")
    @RequestMapping(value = "/exciting", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Boolean exciting(HttpServletRequest request, HttpServletResponse response,
                            @ApiParam("id") @RequestParam(required = true) Long id){
        SubscriptionFollower byId = subscriptionFollowerManager.findById(id);
        if (byId!=null){
            return true;
        }
        return false;
    }

    @ApiOperation("findByNickname")
    @RequestMapping(value = "/findByNickname", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Page<SubscriptionFollower> findByNickname(HttpServletRequest request, HttpServletResponse response,
                            @ApiParam("Nickname") @RequestParam(required = true) String Nickname){

        Pageable pageable = new PageRequest(0,10);
        Page<SubscriptionFollower> page = subscriptionFollowerManager.findByNickname(pageable,Nickname);
        return page;
    }
    /**
     *
     * 扫描用户列表，检测最后一个用户是否为添加的用户，如果不是继续检测，超时时间为60s
     * @param
     * @return
     */
    @ApiOperation("添加关注")
    @RequestMapping(value = "/scanFollower", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public SubscriptionFollower scanFollower(HttpServletRequest request, HttpServletResponse response,
                                             @ApiParam("wechatName") @RequestParam(required = true) String wechatName){
        List<SubscriptionFollower> sFcontent = subscriptionFollowerManager.findByNickname(DEFAL_PAGEABLE, wechatName).getContent();
        if (sFcontent!=null&&sFcontent.size()>0){
            return sFcontent.get(0);
        }
        List<String> userList = wechatUtil.getUserList();
        String openId = userList.get(userList.size()-1);
        SubscriptionFollower lastFollower = wechatUtil.getUserInfo(openId);
        if (lastFollower.getNickname().equals(wechatName)){
            subscriptionFollowerManager.save(lastFollower);
            return lastFollower;
        }
        for (int i=0;i<5;i++){
            try {
                Thread.sleep(20000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            userList = wechatUtil.getUserList(openId);
            if (userList!=null&&userList.size()>0){
                for (String s:userList){
                    SubscriptionFollower follower = wechatUtil.getUserInfo(s);
                    if (follower.getNickname().equals(wechatName)){
                        subscriptionFollowerManager.save(follower);
                        return follower;
                    }
                }
            }else continue;
        }
        SubscriptionFollower timeOut = new SubscriptionFollower();
        timeOut.setRemark("time out");
        return timeOut;
    }


}
