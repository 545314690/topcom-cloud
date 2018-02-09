package com.topcom.cms.web.spring.controller;

import com.topcom.cms.perm.annotation.PublicResource;
import com.topcom.cms.utils.RandomVerifyCode;
import com.topcom.cms.utils.SubjectUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static com.topcom.cms.utils.RandomVerifyCode.codeHeader;

/**
 * Created by lism on 2017/5/25.
 */
@Controller
@RequestMapping("service")
@PublicResource
public class ServiceController {


    @RequestMapping(method = RequestMethod.GET, value = "verifyCode2")
    public void verifyCode2(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);

        /**
         * 加上这个前台ajax才能获得自定义的header
         * response.setHeader("Access-Control-Expose-Headers", "custom header");
         * response.setHeader("costum header", "custom header value");
         */
        response.setHeader("Access-Control-Expose-Headers", codeHeader);
        String code = UUID.randomUUID().toString();
        response.setHeader(codeHeader, code);
        RandomVerifyCode randomVerifyCode = new RandomVerifyCode();
        try {
            String verifyCode = randomVerifyCode.writeRandcode(request, response);// 输出图片方法
            SubjectUtil.setCaptcha(code, verifyCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "verifyCode")
    @ResponseBody
    public String verifyCode(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);

        /**
         * 加上这个前台ajax才能获得自定义的header
         * response.setHeader("Access-Control-Expose-Headers", "custom header");
         * response.setHeader("costum header", "custom header value");
         */
        response.setHeader("Access-Control-Expose-Headers", codeHeader);
        String code = UUID.randomUUID().toString();
        response.setHeader(codeHeader, code);
        RandomVerifyCode randomVerifyCode = new RandomVerifyCode();
        try {
            RandomVerifyCode.VerifyCode verifyCode = randomVerifyCode.getBase64Randcode();// 输出图片方法
            if (verifyCode != null) {
                SubjectUtil.setCaptcha(code, verifyCode.code);
                return verifyCode.image;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
