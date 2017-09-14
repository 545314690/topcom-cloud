package com.topcom.cms.web.spring.interceptor;

import com.topcom.cms.domain.User;
import com.topcom.cms.perm.exception.AuthenticationException;
import com.topcom.cms.perm.exception.UnLoginException;
import com.topcom.cms.utils.SubjectUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 资源拦截器，检查是否用户登录
 *
 * @author lisenmiao
 */
@Component
public class LoginInterceptor extends BaseInterceptor implements HandlerInterceptor {


    protected static final String AUTHORIZATION_HEADER = "Authorization";

    /**
     * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * <p>
     * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
     * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (isOptionRequest(request,response) || logined(request)) {
            return true;
        } else {
            // 用户未登录，记录当前链接，转登录页面
            recordLastInfo(request);
            throw new UnLoginException();
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }


    /**
     * 记录上一次的用户操作的url
     *
     * @param request
     */
    private void recordLastInfo(HttpServletRequest request) {
        // 当前用户的刷新或者其他操作的uri
        String uri = request.getRequestURI();
        // 得到完整的路径和提交方式
        if (request.getMethod().toUpperCase().equals("POST")) {
            request.getSession().setAttribute("method", "POST");
        } else {
            request.getSession().setAttribute("method", "GET");
        }
        String params = request.getQueryString();
        if (StringUtils.isNotBlank(params)) {
            uri += "?" + params;
        }
        request.getSession().setAttribute("lasturi", uri);

    }

    /**
     * 判断用户是否已登录
     *
     * @param request
     * @return
     */
    private boolean logined(HttpServletRequest request) throws AuthenticationException {
        boolean result = false;
        User loginUser = SubjectUtil.getCurrentUser(request);
        if (null != loginUser) {
            result = true;
        }
        return result;
    }


    protected String getAuthzHeader(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION_HEADER);
    }
}
