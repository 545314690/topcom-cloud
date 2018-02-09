package com.topcom.cms.perm.exception;

/**
 * Created by lism on 2017/5/25.
 */
public class LoginExceptionHandler {
    public static String message(AuthenticationException e) {
        String exceptionClassName = e.getClass().getName();
        String error = null;
        if(e.getMessage() != null){
            error = e.getMessage();
        }else{
            if (UnLoginException.class.getName().equals(exceptionClassName)) {
                error = "用户未登陆";
            } else if (VerifyCodeErrorException.class.getName().equals(exceptionClassName)) {
                error = "验证码错误";
            } else if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                error = "用户名不存在";
            } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                error = "用户名/密码错误";
            } else if (ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {
                error = "登录失败次数过多,账户已被锁定";
            } else if (AccountUnavailableException.class.getName().equals(exceptionClassName)) {
                error = "账号不可用，请联系管理员";
            } else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
                error = "账号被锁定，请联系管理员";
            }else if (exceptionClassName != null) {
                error = "登录失败";
            }
        }
        return error;
    }
}
