package com.topcom.cms.yuqing.feignclients;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserClientHystrix implements UserClient {
//    @Override
//    public Integer add(Integer a, Integer b) {
//        return -9999;
//    }

    @Override
    public boolean exists(String username) {
        return false;
    }

    @Override
    public JSONObject getCurrentUser() {
        return null;
    }

    @Override
    public Map login(String username, String password) {
        return null;
    }
}
