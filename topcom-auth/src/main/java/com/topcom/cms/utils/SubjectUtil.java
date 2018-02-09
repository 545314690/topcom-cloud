package com.topcom.cms.utils;

import com.topcom.cms.config.Constants;
import com.topcom.cms.domain.User;
import com.topcom.cms.perm.exception.*;
import com.topcom.cms.perm.token.TokenManager;
import com.topcom.cms.perm.token.UsernamePasswordToken;
import com.topcom.cms.service.UserManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
    public static User getCurrentUser() throws AuthenticationException {
        return getCurrentUser(getRequest());
    }

    public static HttpServletRequest getRequest() {
        //TODO:单元测试的时候，还是会得到requestAttributes，不等于null
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
    public static User getCurrentUser(HttpServletRequest request) throws AuthenticationException {
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

    public static Set<String> getPermissions(HttpServletRequest request) throws AuthenticationException {
        User user = getCurrentUser(request);
        return user.getPermissions();
    }
    public static Set<String> getPermissions(String token) throws UnLoginException {
        return getCurrentUser(token).getPermissions();
    }
    public static User getCurrentUser(String token) throws UnLoginException  {
        if (token == null) {
            return null;
        }
        if (!SubjectUtil.isValidToken(token)) {
            return null;
        }
        User user = (User) tokenManager.getTokenObject(token);
        return user;
    }

    public static boolean login(UsernamePasswordToken token) throws AuthenticationException {

        String username = token.getUsername();
        User user = userManager.findByUsername(username);

        if (user == null) {
            throw new UnknownAccountException();//没找到帐号
        }
        if (User.State.UNAVAILABLE.equals(user.getState())) {
            throw new AccountUnavailableException(); //帐号不可用
        }
        if (User.State.LOCKED.equals(user.getState())) {
            throw new LockedAccountException(); //帐号锁定
        }
        if(token.getAdmin() != null && token.getAdmin() == true){
            Boolean admin = user.isAdmin();
            if(admin == null || admin != true){
                throw new UnknownAccountException("不是管理员帐号,不能登录");//不是管理员帐号
            }
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

    /**
     * 获取验证码
     *
     * @param code
     * @return
     */
    public static String getCaptcha(String code) {
        return tokenManager.getCaptcha(code);
    }

    /**
     * 保存验证码
     *
     * @param code
     */
    public static void setCaptcha(String code, String captcha) {
        tokenManager.setCaptcha(code, captcha);
    }

}
