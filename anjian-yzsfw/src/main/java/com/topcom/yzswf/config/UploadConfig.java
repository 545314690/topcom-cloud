package com.topcom.yzswf.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by topcom on 2017/11/15 0015.
 */
@ConfigurationProperties(prefix = "upload.config")
@Component
/**
 * @author lism
 */
public class UploadConfig {
    /**
     * 文件上传基础路径
     */
    private String baseFolder;
    /**
     * 文档存储文件夹
     */
    private String fileFolder;

    public String getBaseFolder() {
        return baseFolder;
    }

    public void setBaseFolder(String baseFolder) {
        this.baseFolder = baseFolder;
    }

    public String getFileFolder() {
        return fileFolder;
    }

    public void setFileFolder(String fileFolder) {
        this.fileFolder = fileFolder;
    }
}
