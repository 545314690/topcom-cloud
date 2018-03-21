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
        Object oldToken = redisTemplate.boundValueOps(userId + user.getUsername()).get();
        if (oldToken != null) {
            redisTemplate.delete(oldToken);
        }
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
    private final String captchaId = "captcha_";

    @Override
    public void createAndSaveToken(User user) {
        //TODO:防止取出的时候报序列化失败的错，把用户组和角色置为空
        //org.springframework.data.redis.serializer.SerializationException: Could not read JSON: failed to lazily initialize a collection, could not initialize proxy - no Session (through reference chain: com.topcom.cms.domain.User["groups"]); nested exception is com.fasterxml.jackson.databind.JsonMappingException: failed to lazily initialize a collection, could not initialize proxy - no Session (through reference chain: com.topcom.cms.domain.User["groups"])
        //新构造一个user，只保存以下信息
        User userToSave = new User();
        userToSave.setUsername(user.getUsername());
        userToSave.setId(user.getId());
        userToSave.setState(user.getState());

        String token = createToken(userToSave.getUsername());
        LogUtil.logger.info(token);
        saveToken(token, userToSave);
    }

    @Override
    public String findByUsername(String username) {
        return (String) redisTemplate.boundValueOps(userId + username).get();
    }

    @Override
    public String getCaptcha(String code) {
        return (String) redisTemplate.boundValueOps(captchaId + code).get();
    }

    @Override
    public void setCaptcha(String code, String captcha) {
         redisTemplate.boundValueOps(captchaId + code).set(captcha, 3, TimeUnit.MINUTES);//设置验证码3分钟过期;
    }

    public static void main(String[] args) {
        TokenManager tokenManager = new TokenManagerImpl();
        String token = tokenManager.createToken("102");
        User user = new User(102L);
        user.setUsername("sdmj");
        tokenManager.saveToken(token, user);
        System.out.println(token);

        String token2 = tokenManager.createToken("1");
        System.out.println(token2);
    }
}
