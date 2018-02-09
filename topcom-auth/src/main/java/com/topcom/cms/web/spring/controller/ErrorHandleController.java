package com.topcom.cms.web.spring.controller;

import com.topcom.cms.exception.NoHandlerFoundException;
import com.topcom.cms.perm.exception.AuthenticationException;
import com.topcom.cms.perm.exception.UnLoginException;
import com.topcom.cms.perm.exception.UnAuthorizedException;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping({"${server.error.path:${error.path:/error}}"})
public class ErrorHandleController implements ErrorController {
    /**
     * @return
     * @see org.springframework.boot.autoconfigure.web.ErrorController#getErrorPath()
     */
    @Override
    public String getErrorPath() {
        return "/error/404";
    }

    @RequestMapping
    public String errorHandle() {
        return getErrorPath();
    }

    @RequestMapping(method = RequestMethod.GET, value = "403")
    public void _403(HttpServletRequest request, HttpServletResponse response)
            throws UnAuthorizedException {
        throw new UnAuthorizedException();
    }

    @RequestMapping(method = RequestMethod.GET, value = "401")
    public void _401(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        throw new UnLoginException();
    }

    @RequestMapping(method = RequestMethod.GET, value = "404")
    public void _404(HttpServletRequest request, HttpServletResponse response)
            throws NoHandlerFoundException {
        throw new NoHandlerFoundException();
    }
}