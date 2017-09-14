package com.topcom.cms.yuqing.vo.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by maxl on 17-6-8.
 */

/**
 * email 设置
 */
@Configuration
@ConfigurationProperties(prefix = "mail.setting.warning")
public class WarningEmailSetting {

    private String subjectUrl;

    private String logUrl;

    public String getSubjectUrl() {
        return subjectUrl;
    }

    public void setSubjectUrl(String subjectUrl) {
        this.subjectUrl = subjectUrl;
    }

    public String getLogUrl() {
        return logUrl;
    }

    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl;
    }

}
