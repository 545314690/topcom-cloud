package com.topcom.cms.utils;

import com.topcom.cms.domain.User;
import com.topcom.cms.perm.exception.AuthenticationException;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class UserAuditorAware implements AuditorAware<Long> {

    @Override
    public Long getCurrentAuditor() {
        User user = null;
        try {
            user = SubjectUtil.getCurrentUser();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        if (user != null) {
            return user.getId();
        }
        return null;
    }
}