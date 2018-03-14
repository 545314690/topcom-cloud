package com.topcom.yzswf.vo;

import com.topcom.yzswf.util.FileUtil;

import java.io.File;
import java.io.IOException;

/**
 * Created by lsm on 2018/3/12 0012.
 */
public class EsIndexFile {
    private String filename;//文件名
    private String type;//事故案例或者执法文书等
    private String fileType;//文件类型
    private Long downloadTimes = 0L;//文件下载次数
    private Long viewTimes = 0L;//查看次数
    private String filePath;//文件路径
    private Long uploadTime;//上传时间
    private String source;//来源
    private String accidentType;//事故類型

    private String data;//data

    public EsIndexFile() {
    }

    public EsIndexFile(String filePath) throws IOException {
        this.filePath = filePath;
        String data = FileUtil.readContentAsBase64(new File(filePath));
        this.setData(data);
    }

    public String getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(String accidentType) {
        this.accidentType = accidentType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getDownloadTimes() {
        return downloadTimes;
    }

    public void setDownloadTimes(Long downloadTimes) {
        this.downloadTimes = downloadTimes;
    }

    public Long getViewTimes() {
        return viewTimes;
    }

    public void setViewTimes(Long viewTimes) {
        this.viewTimes = viewTimes;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Long uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
