package com.topcom.cms.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConsumerController {
    @Autowired
    UserClient userClient;
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Integer add() {
        return userClient.add(10, 20);
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
}