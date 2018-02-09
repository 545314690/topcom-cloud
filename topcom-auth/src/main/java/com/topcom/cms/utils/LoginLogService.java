package com.topcom.cms.utils;

import com.topcom.cms.domain.LoginLog;
import com.topcom.cms.domain.User;
import com.topcom.cms.service.LoginLogManager;
import com.topcom.cms.service.UserManager;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录日志AOP拦截工具类
 *
 * @author lism
 */
@Aspect
@Component
public class LoginLogService {

    @Resource
    LoginLogManager loginLogManager;

    @Resource
    UserManager userManager;

    // 定义一个切入点
    @Pointcut("execution(* com.topcom.cms.web.spring.controller.LoginController.login(..))")
    private void loginMethod() {
    }


    @AfterReturning("loginMethod()")
    public void doAfter(JoinPoint pjp) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String contextPath = request.getContextPath();
        User user = SubjectUtil.getCurrentUser(request);
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getId());
        loginLog.setUsername(user.getUsername());
        loginLog.setHost(request.getRemoteHost());
        if(StringUtils.isNotBlank(contextPath) && contextPath.length()>0){
            loginLog.setSysName(contextPath.substring(1));
        }

        loginLogManager.save(loginLog);
    }
}