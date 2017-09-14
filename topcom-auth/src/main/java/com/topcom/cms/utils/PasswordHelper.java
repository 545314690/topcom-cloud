package com.topcom.cms.utils;

import com.topcom.cms.common.utils.LogUtil;
import com.topcom.cms.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 *
 */
public class PasswordHelper {

    static Random randomNumberGenerator = new Random();
    @Value("${password.algorithmName}")
    static private String algorithmName = "md5";
    @Value("${password.hashIterations}")
    static private int hashIterations = 2;
    static private int saltLength = 15;

    public void setRandomNumberGenerator(Random randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public static void encryptPassword(User user) {
        byte[] nbyte = new byte[saltLength];
        randomNumberGenerator.nextBytes(nbyte);
        user.setSalt(PasswordEncoder.byteArrayToHexString(nbyte));
        user.setPassword(getEncodedPassword(user.getPassword(), user.getCredentialsSalt()));
    }

    public String getEncodedPassword(User user) {
        return getEncodedPassword(user.getPassword(), user.getCredentialsSalt());
    }

    public static String getEncodedPassword(String password, String credentialsSalt) {
        PasswordEncoder encoder = new PasswordEncoder(credentialsSalt, algorithmName);
        return encoder.encode(password);
    }

    public boolean isPasswordValid(String encodePass, String rawPass, String salt) {
        String pass1 = "" + encodePass;
        PasswordEncoder encoder = new PasswordEncoder(salt, algorithmName);
        String pass2 = encoder.encode(rawPass);
        return pass1.equals(pass2);
    }

    public boolean isPasswordValid(User user, String rawPass) {
        return isPasswordValid(user.getPassword(), rawPass, user.getCredentialsSalt());
    }


    public static void main(String[] args) {
        User u = new User();
        u.setUsername("ahmj");
        u.setPassword("ahmj123");
        PasswordHelper passwordHelper = new PasswordHelper();
        passwordHelper.encryptPassword(u);
        LogUtil.logger.info(u);
    }
}
