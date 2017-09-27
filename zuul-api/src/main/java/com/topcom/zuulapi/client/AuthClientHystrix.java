package com.topcom.zuulapi.client;

import com.topcom.zuulapi.vo.ResponseData;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
public class AuthClientHystrix implements AuthClient {


    @Override
    public ResponseData check(String requestUri, String requestMethod, String token) {
        return new ResponseData(HttpServletResponse.SC_REQUEST_TIMEOUT, "验证权限超时");
    }
}
