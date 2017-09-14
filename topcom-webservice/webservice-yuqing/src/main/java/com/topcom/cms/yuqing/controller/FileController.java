package com.topcom.cms.yuqing.controller;

import com.topcom.cms.yuqing.utils.FileDownloader;
import com.topcom.cms.yuqing.utils.FileServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2016/12/30 0030.
 */
@Controller
@RequestMapping("/file")
public class FileController {
    /**
     * 文件下载
     *
     * @param filename
     * @param request
     * @param response
     * @return
     * @Description:
     */
    @RequestMapping(value = "download/{type}/{filename}", method = RequestMethod.GET)
    public String download(@PathVariable("filename") String filename, @PathVariable("type") String type,
                           HttpServletRequest request, HttpServletResponse response) throws Exception {
        new FileDownloader(filename, type, request, response).invoke();
        return null;
    }

}
