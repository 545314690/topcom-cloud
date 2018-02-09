package com.topcom.zuulapi.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.topcom.zuulapi.client.AuthClient;
import com.topcom.zuulapi.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.logging.Logger;

public class AccessFilter extends ZuulFilter {
    private static Logger log = Logger.getLogger(AccessFilter.class.getName());
    protected static final String AUTHORIZATION_HEADER = "Authorization";


    private String getToken(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION_HEADER);
    }

    private PathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    private AccessFilterConfig config;
    @Autowired
    private AuthClient authClient;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //跨域不拦截OPTIONS方法
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            return false;
        }
        Optional<String> matched = config.getIngorePatterns().stream().filter(pattern -> {
            return pathMatcher.match(pattern, request.getRequestURI());
        }).findFirst();
        return !matched.isPresent();
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        String accessToken = this.getToken(request);
        if(accessToken == null){
            accessToken = request.getParameter("accessToken");
        }
        ctx.getResponse().setContentType("text/html;charset=UTF-8");
        if (accessToken == null) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody(new ResponseData(401, "accessToken is null").toString());// 返回错误内容
            return null;
        } else {
            try {

                ResponseData responseData = authClient.check(request.getRequestURI(), request.getMethod(), accessToken);
                log.info(responseData.toString());
                if (!responseData.getStatus()) {
                    ctx.setSendZuulResponse(false);
                    ctx.setResponseStatusCode(responseData.getCode());
                    ctx.setResponseBody(responseData.toString());// 返回错误内容
                    return responseData;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}