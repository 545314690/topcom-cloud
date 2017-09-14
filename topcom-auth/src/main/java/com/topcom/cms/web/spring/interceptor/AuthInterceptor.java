package com.topcom.cms.web.spring.interceptor;

import com.topcom.cms.domain.Resource;
import com.topcom.cms.perm.exception.UnauthorizedException;
import com.topcom.cms.service.ResourceManager;
import com.topcom.cms.utils.SubjectUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 资源拦截器，检查是否用户登录
 *
 * @author lisenmiao
 */
@Component
public class AuthInterceptor extends BaseInterceptor implements HandlerInterceptor {
    private Logger log = Logger.getLogger(AuthInterceptor.class);

    /**
     * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * <p>
     * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
     * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (isOptionRequest(request,response)) {
            return true;
        }
        if (hasPermission(request)) {
            return true;
        } else {
            throw new UnauthorizedException();
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
     * 验证用户是否有访问该页面的权限
     *
     * @param request
     * @return 是否有权限
     */
    private boolean hasPermission(HttpServletRequest request) throws Exception {
        if(map.isEmpty()){
            return true;
        }
        boolean permit = false;
        String path = request.getContextPath();
        String uri = request.getRequestURI().replace(path, "");
        String permString = map.get(uri);
        Set<String> permissions = SubjectUtil.getPermissions(request);
        if (permissions != null && permissions.contains(permString)) {
            permit = true;
        }
        // 判断是否是排除的链接
        return permit;
    }

    private Map<String, String> map = new ConcurrentHashMap<>();

    public AuthInterceptor(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    @Autowired
    private ResourceManager resourceManager;

    @PostConstruct
    void registerPermissionMap() {

        // 从数据库获取
        List<Resource> list = resourceManager.getAllResources();

        List<String> paths = new ArrayList<String>();
        list.stream()
                .filter(resource -> resource != null && resource.getUrl() != null && resource.getPermission() != null)
                .forEach(
                        resource -> {
                            map.put(resource.getUrl(), resource.getPermission());
                            paths.add(resource.getUrl());
                        }
                );
        this.setPaths(paths);
    }
}
