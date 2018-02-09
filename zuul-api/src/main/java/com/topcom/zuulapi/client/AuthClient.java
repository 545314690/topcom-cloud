package com.topcom.zuulapi.client;

import com.topcom.zuulapi.vo.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


//@FeignClient(value = "auth")
@FeignClient(value = "auth", fallback = AuthClientHystrix.class)
public interface AuthClient {
    @RequestMapping(method = RequestMethod.POST, value = "/perm/check")
    ResponseData check(@RequestParam(value = "requestUri") String requestUri,
                       @RequestParam(value = "requestMethod") String requestMethod,
                       @RequestParam(value = "accessToken") String token);
}