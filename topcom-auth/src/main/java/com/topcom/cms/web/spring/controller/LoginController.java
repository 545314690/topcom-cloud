package com.topcom.cms.web.spring.controller;

import com.topcom.cms.config.Constants;
import com.topcom.cms.domain.User;
import com.topcom.cms.perm.annotation.PublicResource;
import com.topcom.cms.perm.exception.AuthenticationException;
import com.topcom.cms.perm.exception.UnLoginException;
import com.topcom.cms.perm.exception.VerifyCodeErrorException;
import com.topcom.cms.service.UserManager;
import com.topcom.cms.utils.RandomVerifyCode;
import com.topcom.cms.utils.SubjectUtil;
import com.topcom.cms.perm.token.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;


/**
 * 用户登录界面信息维护的交互控制
 *
 * @author lisenmiao
 */
@Controller
@RequestMapping("login")
@PublicResource
public class LoginController {

    @ResponseBody
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logoutui(HttpServletRequest request) {
        SubjectUtil.logout();
        return "logout success";
    }

    @ResponseBody
    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String loginSuccess(HttpServletRequest request) {
        return "login success";
    }

    /**
     * 登录验证 登录成功后返回用户信息
     *
     * @param request
     * @param response
     * @param username
     * @param password
     * @param captcha
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap login(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam String username,
                          @RequestParam String password,
                          @RequestParam(required = true) String captcha,
                          @RequestParam(required = false) Boolean admin,
                          @RequestParam(required = false) Boolean rememberMe) throws AuthenticationException {

        String code = request.getHeader(RandomVerifyCode.codeHeader);
        if (code == null || captcha == null) {
            throw new VerifyCodeErrorException();
        }
        String captchaOnserver = SubjectUtil.getCaptcha(code);
        if(!captcha.equalsIgnoreCase(captchaOnserver)){
            throw new VerifyCodeErrorException();
        }

        return login2(request,response,username,password,captcha,admin,rememberMe);

    }

    /**
     * 登录验证 登录成功后返回用户信息(为了测试，去掉验证码信息)
     *
     * @param request
     * @param response
     * @param username
     * @param password
     * @param captcha
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/login2", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap login2(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam String username,
                          @RequestParam String password,
                          @RequestParam(required = false) String captcha,
                          @RequestParam(required = false) Boolean admin,
                          @RequestParam(required = false) Boolean rememberMe) throws AuthenticationException {


        ModelMap modelMap = new ModelMap("success", false);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        if(admin != null && admin == true ){
            usernamePasswordToken.setAdmin(admin);
        }
        boolean logined = SubjectUtil.login(usernamePasswordToken);
        // 验证是否登录成功
        if (logined) {
            modelMap.put("success", true);
            modelMap.put("message", "登录成功");

            String token = SubjectUtil.getLoginToken(username);
            User currentUser = SubjectUtil.getCurrentUser(token);
            request.getSession().setAttribute(Constants.CURRENT_USER, currentUser);
            modelMap.put("token", token);
            modelMap.put("user", currentUser.getFullName() == null ? username : currentUser.getFullName());
        } else {
            throw new AuthenticationException();
        }

        return modelMap;

    }
}
