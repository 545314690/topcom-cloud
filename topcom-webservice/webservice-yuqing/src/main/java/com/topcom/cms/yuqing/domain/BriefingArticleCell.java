package com.topcom.cms.yuqing.domain;

import java.io.Serializable;

/**
 * Created by lism on 15:24.
 * Author：<a href="mailto:545314690@qq.om">lisenmiao</a>
 */
public class BriefingArticleCell implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String content = "";

    private String pubTime = "";

    private String source = "";

    private String url = "";

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
