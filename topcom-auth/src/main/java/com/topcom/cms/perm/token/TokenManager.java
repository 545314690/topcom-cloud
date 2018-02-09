package com.topcom.cms.perm.token;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.topcom.cms.common.utils.LogUtil;
import com.topcom.cms.domain.User;
import com.topcom.cms.perm.token.verifier.MACVerifierExtended;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

;

public interface TokenManager {

    default byte[] generateSharedKey() {
        SecureRandom random = new SecureRandom();
        byte[] sharedKey = new byte[32];
        random.nextBytes(sharedKey);
        return sharedKey;
    }

    default long getExpirationDate() {
        return 1000 * 60 * 60 * 24 * 7;
    }

    public String getIssuer();

    default public JWSObject parseToken(String token) throws ParseException {
        JWSObject jwsObject = JWSObject.parse(token);
        return jwsObject;
    }

    ;

    default public String getSub(String token) throws ParseException {
        JWSObject jwsObject = JWSObject.parse(token);
        String decrypted = jwsObject.getPayload().toString();

        try (JsonReader jr = Json.createReader(new StringReader(decrypted))) {
            JsonObject object = jr.readObject();

            return object.getString("sub");
        }
    }

    ;

    public byte[] getSharedKey();

    public String saveToken(String token, User user);


    default String createAndSaveToken(String userId, User user) {
        String token = createToken(userId);
        LogUtil.logger.info(token);
        saveToken(token, (user));
//        saveToken(token, ((User)user).getUsername());
        return token;
    }

    default String createToken(Object userId) {
        try {
            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();

            builder.issuer(getIssuer());
            builder.subject(userId.toString());
            builder.issueTime(new Date());
            builder.notBeforeTime(new Date());
            builder.expirationTime(new Date(System.currentTimeMillis() + getExpirationDate()));
            builder.jwtID(UUID.randomUUID().toString());

            JWTClaimsSet claimsSet = builder.build();
            JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

            Payload payload = new Payload(claimsSet.toJSONObject());

            JWSObject jwsObject = new JWSObject(header, payload);

            JWSSigner signer = new MACSigner(getSharedKey());
            jwsObject.sign(signer);
            return jwsObject.serialize();
        } catch (JOSEException ex) {
            return null;
        }
    }

    default boolean validateToken(String token) {

        if (token == null) {
            return false;
        }
        try {
            SignedJWT signed = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifierExtended(getSharedKey(), signed.getJWTClaimsSet());
            return signed.verify(verifier);
        } catch (ParseException ex) {
            return false;
        } catch (JOSEException ex) {
            return false;
        }

    }

    /**
     * 清除token
     *
     * @param token
     */
    public void deleteToken(Object token);

    public Object getTokenObject(Object token);

    void createAndSaveToken(User user);

    String findByUsername(String username);

    /**
     * 获取验证码
     * @param code
     * @return
     */
    String getCaptcha(String code);

    /**
     * 保存验证码
     * @param code
     */
    void setCaptcha(String code,String captcha);
}
