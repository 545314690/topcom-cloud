package com.topcom.cms.utils;

import com.topcom.cms.common.utils.LogUtil;
import com.topcom.cms.domain.User;
import com.topcom.cms.perm.exception.UnLoginException;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserAuditorAware implements AuditorAware<Long>{

    @Override
    public Long getCurrentAuditor() {
        User user = null;
        try {
            user = SubjectUtil.getCurrentUser();
        } catch (UnLoginException e) {
            e.printStackTrace();
        }
        if (user != null) return user.getId();
        return null;
    }
    public static void main(String[] args) {
        User u = new User();
        u.setUsername("node_service");
        u.setPassword("node_service_topcom");
        PasswordHelper passwordHelper = new PasswordHelper();
        passwordHelper.encryptPassword(u);
        LogUtil.logger.info(u);
    }
}  