package com.topcom.cms.web.bind.method;

import com.topcom.cms.domain.User;
import com.topcom.cms.perm.exception.UnLoginException;
import com.topcom.cms.utils.SubjectUtil;
import com.topcom.cms.web.bind.annotation.CurrentUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 用于绑定@CurrentUser的方法参数解析器
 *
 * @author lism
 */
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    public CurrentUserMethodArgumentResolver() {
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.getParameterType().isAssignableFrom(User.class) && parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        CurrentUser currentUserAnnotation = parameter.getParameterAnnotation(CurrentUser.class);
        //从Session 获取用户
        Object object = webRequest.getAttribute(currentUserAnnotation.value(), NativeWebRequest.SCOPE_SESSION);
        //如果用户未登陆，抛出异常
        if (object == null) {
//            throw new UnLoginException();
//            return new User(100L);
            String token = webRequest.getHeader("Authorization");
            if (token == null) {
                throw new UnLoginException();
            } else {
                User obj = SubjectUtil.getCurrentUser(token);
                if (obj == null) {
                    throw new UnLoginException();
                } else {
                    return obj;
                }
            }
        }
        return object;
    }
}
