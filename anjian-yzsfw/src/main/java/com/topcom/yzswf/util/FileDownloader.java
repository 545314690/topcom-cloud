package com.topcom.yzswf.util;

import com.topcom.cms.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileDownloader {
    private String filename;
    private String filepath;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public FileDownloader(String filename, String filepath, HttpServletRequest request, HttpServletResponse response) {
        this.filename = filename;
        this.filepath = filepath;
        this.request = request;
        this.response = response;
    }

    public void invoke() throws UnsupportedEncodingException, BusinessException {
        if (filepath != null) {
            File file = new File(filepath);
            if (file.exists()) {
             /*
            * 解决各浏览器的中文乱码问题
            */
                String userAgent = request.getHeader("User-Agent");
                byte[] bytes = userAgent.contains("MSIE") ? filename.getBytes()
                        : filename.getBytes("UTF-8"); // filename.getBytes("UTF-8")处理safari的乱码问题
                filename = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码

                response.reset();
                response.setHeader("Content-Disposition", "attachment; filename=\""
                        + filename + "\"");
                // 向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                // 前台页面不使用缓存 因为各个浏览器可能不同，所以写了三种
                response.setDateHeader("Expires", 0);
                response.setHeader("Cache-Controll", "no-cache");
                response.setHeader("pragma", "no-cache");
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                throw new BusinessException("文件不存在");
            }
        } else {
            throw new BusinessException("文件名不能为空");
        }
    }
}