package com.topcom.cms.utils;

import com.topcom.cms.domain.Resource;
import com.topcom.cms.domain.User;
import com.topcom.cms.perm.exception.AuthenticationException;
import com.topcom.cms.perm.exception.UnLoginException;
import com.topcom.cms.service.ResourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by topcom on 2017/9/21 0021.
 */
@Component
public class PermissionUtils {
    private Map<String, String> map = new ConcurrentHashMap<>();

    @Autowired
    private ResourceManager resourceManager;

    public Map getPermissionMessage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map result =  new HashMap<>();
        if (!preHandle(request,response)){
            result.put("message","用户未登录");

        }else {
            if (!hasPermission(request)){
                result.put("message","此用户无权限");
            }else {
                result.put("message","success");
            }
        }
        return result;
    }




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

    //检测用户是否登录，如果返回false返回未登录信息，如果true 继续验证是否有权限
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        if (isOptionRequest(request,response) || logined(request)) {
            return true;
        } else {
            // 用户未登录，记录当前链接，转登录页面
            //recordLastInfo(request);
            throw new UnLoginException();
        }
    }

    protected boolean isOptionRequest(HttpServletRequest request, HttpServletResponse response) {
//        if(request.getMethod().equals(RequestMethod.OPTIONS.name()))
//        {
//            response.setStatus(HttpStatus.OK.value());
//            return true;
//        }else{
//            return false;
//        }
        String access_headers = request.getHeader("access-control-request-headers");
        if (access_headers != null && access_headers.toLowerCase().contains("authorization")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean logined(HttpServletRequest request) throws AuthenticationException {
        boolean result = false;
        User loginUser = SubjectUtil.getCurrentUser(request);
        if (null != loginUser) {
            result = true;
        }
        return result;
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
