package com.topcom.cms.web.spring.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by lism on 17-6-13.
 */
public class BaseInterceptor {

    private String[] excludePaths = new String[]{};
    private String[] paths = new String[]{};

    public String[] getExcludePaths() {
        return excludePaths;
    }

    public void setExcludePaths(String[] excludePaths) {
        this.excludePaths = excludePaths;
    }

    public String[] getPaths() {
        return paths;
    }

    public void setPaths(String[] paths) {
        this.paths = paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths.toArray(this.paths);
    }

    /**
     * 判断是不是OPTIONS请求
     *
     * @param request
     * @return
     */
    protected boolean isOptionRequest(HttpServletRequest request, HttpServletResponse response) {
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            response.setStatus(HttpStatus.OK.value());
            return true;
        }
// else{
//            return false;
//        }
        String access_headers = request.getHeader("access-control-request-headers");
        if (access_headers != null && access_headers.toLowerCase().contains("authorization")) {
            return true;
        } else {
            return false;
        }
    }
}
