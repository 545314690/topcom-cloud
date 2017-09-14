package com.topcom.cms.web.spring.controller;

import com.topcom.cms.utils.RandomVerifyCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lism on 2017/5/25.
 */
@Controller
@RequestMapping("service")
public class ServiceController {


    @RequestMapping(method = RequestMethod.GET, value = "verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomVerifyCode randomVerifyCode = new RandomVerifyCode();
        try {
            randomVerifyCode.getRandcode(request, response);// 输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
