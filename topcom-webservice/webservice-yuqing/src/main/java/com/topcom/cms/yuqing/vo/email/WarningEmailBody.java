package com.topcom.cms.yuqing.vo.email;

import java.util.List;

/**
 * Created by maxl on 17-6-8.
 */

/**
 * 舆情预警email
 */
public class WarningEmailBody {
    private String user;
    private String specialUrl;
    private String warningLogUrl;
    private String specialName;
    private String specialNumber;
    private List content;
    private String subject = "舆情预警";//

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public WarningEmailBody(String user, String specialUrl, String specialName, String specialNumber, List content, String subject) {
        this.user = user;
        this.specialUrl = specialUrl;
        this.specialName = specialName;
        this.specialNumber = specialNumber;
        this.content = content;
        this.subject = subject;
    }

    public WarningEmailBody() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSpecialUrl() {
        return specialUrl;
    }

    public void setSpecialUrl(String specialUrl) {
        this.specialUrl = specialUrl;
    }

    public String getSpecialName() {
        return specialName;
    }

    public void setSpecialName(String specialName) {
        this.specialName = specialName;
    }

    public String getSpecialNumber() {
        return specialNumber;
    }

    public void setSpecialNumber(String specialNumber) {
        this.specialNumber = specialNumber;
    }

    public List getContent() {
        return content;
    }

    public void setContent(List content) {
        this.content = content;
    }

    public String getWarningLogUrl() {
        return warningLogUrl;
    }

    public void setWarningLogUrl(String warningLogUrl) {
        this.warningLogUrl = warningLogUrl;
    }
}
