package com.topcom.cms.yuqing.feignclients;

import com.topcom.cms.yuqing.bind.annotation.CurrentUser;
import com.topcom.cms.yuqing.domain.User;
import io.swagger.annotations.Api;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/test")
@Api("ConsumerController")
@RestController
public class ConsumerController {

    @Autowired
    UserClient userClient;


    @ResponseBody
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public User add(@CurrentUser User user) {
        return user;
    }

    /**
     * 根据用户名查询用户是否存在
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/exists/{username}", method = RequestMethod.GET)
    @ResponseBody
    public boolean exists(@PathVariable String username) {
        return userClient.exists(username);
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Map login(@RequestParam String username, @RequestParam String password) {
        return userClient.login(username,password);
    }


}