package com.topcom.cms.yuqing.test;

import org.springframework.stereotype.Component;

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
}
