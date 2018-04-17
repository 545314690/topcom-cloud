package com.topcom.bi.controller;

import com.topcom.bi.config.UploadConfig;
import com.topcom.bi.domain.UploadFile;
import com.topcom.bi.utils.FileUploader;
import com.topcom.bi.utils.ReadExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author maxl
 * @date 2017/12/15 0015
 */
@RestController
@RequestMapping("file")
public class FileUploadController {
    @Autowired
    private FileUploader fileUploader;
    @Autowired
    private UploadConfig uploadConfig;

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 返回路径
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> upload(
            @RequestParam(value = "file") MultipartFile file) throws Exception {
        Map<String, String> map = new HashMap();
        UploadFile uploadFile = fileUploader.upload(file, uploadConfig.getFileFolder());
        //绝对路径
        String filePath = uploadConfig.getBaseFolder() + File.separatorChar + uploadFile.getFilepath();
        ReadExcelUtils excelUtils = new ReadExcelUtils(filePath);
        try {
            String[] title = excelUtils.readExcelTitle();
            List<Map<String, Object>> content = excelUtils.readExcelContent(title);
            map = excelUtils.toTxtFile(filePath, content);
            String titleStr = "";
            for (int i = 0; i < title.length; i++) {
                titleStr += title[i];
                if (i < title.length - 1) {
                    titleStr += ",";
                } else {
                    titleStr += "\n";
                }
            }
            String contentTxt = titleStr + map.get("content");
            map.put("content", contentTxt);
            map.remove("filepath");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
