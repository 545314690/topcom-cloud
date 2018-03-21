package com.topcom.cms.utils;

import com.topcom.cms.domain.Resource;
import com.topcom.cms.perm.exception.AuthenticationException;
import com.topcom.cms.perm.exception.UnAuthorizedException;
import com.topcom.cms.perm.exception.UnLoginException;
import com.topcom.cms.service.ResourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by topcom on 2017/9/21 0021.
 */
@Component
public class PermissionUtils {
    private Map<String, String> map = new ConcurrentHashMap<>();

    @Autowired
    private ResourceManager resourceManager;

    /**
     * 检测用户是否登录，如果返回false 抛出未登录信息异常，
     * 如果true 继续验证是否有权限
     */

    public boolean checkPermission(HttpServletRequest request)
            throws Exception {
        if (!logined(request)) {
            throw new UnLoginException();
        }
        if (!hasPermission(request)) {
            throw new UnAuthorizedException();
        }
        return true;
    }

    public boolean checkPermission(String requestUri,String requestMethod,String token)
            throws Exception {
        if (!logined(token)) {
            throw new UnLoginException();
        }
        if (!hasPermission(token,requestUri,requestMethod)) {
            throw new UnAuthorizedException();
        }
        return true;
    }

    //TODO:请求method没有处理
    private boolean hasPermission(String token,String requestUri,String requestMethod)throws Exception {
        if (map.isEmpty()) {
            return true;
        }
        boolean permit = false;
        String permString = map.get(requestUri);

        //TODO:由于用户的权限没有保存到redis数据库user对象中，这个地方暂未实现
        Set<String> permissions = SubjectUtil.getPermissions(token);
        if (permissions != null && permissions.contains(permString)) {
            permit = true;
        }
        // 判断是否是排除的链接
        return permit;
    }
    private boolean hasPermission(HttpServletRequest request) throws Exception {
        if (map.isEmpty()) {
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


    private boolean logined(HttpServletRequest request) throws AuthenticationException {
        return SubjectUtil.getCurrentUser(request) != null;
    }

    private boolean logined(String token) throws AuthenticationException {
        return SubjectUtil.getCurrentUser(token) != null;
    }


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
        // this.setPaths(paths);
    }
}
