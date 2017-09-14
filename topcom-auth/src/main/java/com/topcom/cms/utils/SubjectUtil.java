package com.topcom.cms.utils;

import com.topcom.cms.config.Constants;
import com.topcom.cms.domain.User;
import com.topcom.cms.perm.exception.*;
import com.topcom.cms.service.UserManager;
import com.topcom.cms.perm.token.TokenManager;
import com.topcom.cms.perm.token.UsernamePasswordToken;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;


/**
 * 获得session 、user等
 *
 * @author <a href="mailto:545314690@qq.om">lisenmiao</a>
 * @date 2014年8月11日上午10:16:35
 */
@Component
public class SubjectUtil {


    protected static final String AUTHORIZATION_HEADER = "Authorization";
    public static TokenManager tokenManager;


    private static UserManager userManager;

    @Autowired
    public void setUserManager(UserManager userManager) {
        SubjectUtil.userManager = userManager;
    }

    @Autowired
    public void setTokenManager(TokenManager tokenManager) {
        SubjectUtil.tokenManager = tokenManager;
    }

    protected static String getAuthzHeader(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION_HEADER);
    }

    /**
     * 优先获得session中存储的user对象，再去根据token拿
     *
     * @return
     */
    public static User getCurrentUser() throws UnLoginException {
        return getCurrentUser(getRequest());
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            return requestAttributes.getRequest();
        } else {
            return null;
        }
    }

    /**
     * 优先获得session中存储的user对象，再去根据token拿
     *
     * @param request
     * @return
     */
    public static User getCurrentUser(HttpServletRequest request) throws UnLoginException {
        if (request == null) {
            return null;
        }
        Object object = request.getSession().getAttribute(Constants.CURRENT_USER);
        if (object != null) {
            return (User) object;
        }
        String authzHeader = getAuthzHeader(request);
        return getCurrentUser(authzHeader);
    }

    public static Set<String> getPermissions(HttpServletRequest request) throws UnLoginException {
        User user = getCurrentUser(request);
        return user.getPermissions();
    }

    public static User getCurrentUser(String token) throws UnLoginException {
        if (!SubjectUtil.isValidToken(token)) {
            return null;
        }
        if (token == null) {
            throw new UnLoginException();
        }
        User user = (User) tokenManager.getTokenObject(token);
        if (user == null) {
            user = new User(100L);
        }
        return user;
    }

    public static boolean login(UsernamePasswordToken token) throws AuthenticationException {

        String username = token.getUsername();
        User user = userManager.findByUsername(username);

        if (user == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        if (User.State.LOCKED.equals(user.getState())) {
            throw new LockedAccountException(); //帐号锁定
        }
        boolean matched = true;
        String loginPassword = token.getPassword();

        String credentialsSalt = user.getCredentialsSalt();
        String password = user.getPassword();

        String encodedPassword = PasswordHelper.getEncodedPassword(loginPassword, credentialsSalt);
        if (!StringUtils.equals(encodedPassword, password)) {
            matched = false;
            throw new IncorrectCredentialsException();
        } else {
            tokenManager.createAndSaveToken(user);
//            resetRetryTimes(username);
        }
        return matched;
    }


    public static void logout() {
        HttpServletRequest request = getRequest();
        logout(request);
    }

    public static void logout(HttpServletRequest request) {
        request.getSession().invalidate();
        String authzHeader = null;
        try {
            authzHeader = getAuthzHeader(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (authzHeader != null) {
            tokenManager.deleteToken(authzHeader);
        }
    }

    public static String getLoginToken(String username) {
        return tokenManager.findByUsername(username);
    }

    public static boolean isValidToken(String token) {
        return tokenManager.validateToken(token);
    }
}
