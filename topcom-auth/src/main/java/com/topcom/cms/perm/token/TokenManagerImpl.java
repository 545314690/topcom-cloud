package com.topcom.cms.perm.token;


import com.topcom.cms.common.utils.LogUtil;
import com.topcom.cms.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by lism on 17-6-11.
 */
@Component
public class TokenManagerImpl implements TokenManager {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String getIssuer() {
        return "bjtopcom";
    }

    @Override
    public byte[] getSharedKey() {
        return "bjtopcomsdddadsfdddddddddddddddddddddddddddddddddddaaaaasdafafafaf".getBytes();
    }

    @Override
    public String saveToken(String token, User user) {
        redisTemplate.boundValueOps(userId + user.getUsername()).set(token, getExpirationDate(), TimeUnit.SECONDS);//设置token,username
        redisTemplate.boundValueOps(tokenId + token).set(user, getExpirationDate(), TimeUnit.SECONDS);//设置token,username
        //        redisTemplate.boundSetOps(user.getUsername()).diff(user.getPermissions());//设置username,permissions
        return token.toString();
    }

    @Override
    public void deleteToken(Object token) {
        redisTemplate.delete(tokenId + token);
        //delete permission
    }

    @Override
    public Object getTokenObject(Object token) {
        return redisTemplate.boundValueOps(tokenId + token).get();
    }

    private final String userId = "username_";
    private final String tokenId = "token_";

    @Override
    public void createAndSaveToken(User user) {
        String token = createToken(user.getUsername());
        LogUtil.logger.info(token);
        saveToken(token, user);
    }

    @Override
    public String findByUsername(String username) {
        return (String) redisTemplate.boundValueOps(userId + username).get();
    }
}
