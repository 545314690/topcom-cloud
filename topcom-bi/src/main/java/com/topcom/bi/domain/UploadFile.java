package com.topcom.bi.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/3 0003.
 *
 * @author lism
 */
public class UploadFile implements Serializable {
    private String filename;
    private String filepath;
    private Long uploadTime;
    private String fileType;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Long uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }


    @Override
    public String toString() {
        return "UploadFile{" +
                "filename='" + filename + '\'' +
                ", filepath='" + filepath + '\'' +
                ", uploadTime=" + uploadTime +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}
