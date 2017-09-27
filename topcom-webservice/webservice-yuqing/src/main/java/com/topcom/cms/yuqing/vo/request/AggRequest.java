package com.topcom.cms.yuqing.vo.request;

import com.topcom.cms.common.page.DateParam;

import java.io.Serializable;

/**
 * Created by lism on 2017/5/24.
 */
public class AggRequest implements Serializable {
    private DateParam date = new DateParam();
    private KeywordRequest keywordRequest = new KeywordRequest();
    private String groupBy;
    private String type;

    public AggRequest() {
    }

    public AggRequest(KeywordRequest keywordRequest) {
        this.keywordRequest = keywordRequest;
    }

    public AggRequest(DateParam date, KeywordRequest keywordRequest) {
        this.date = date;
        this.keywordRequest = keywordRequest;
    }
    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }
    public DateParam getDate() {
        return date;
    }

    public void setDate(DateParam date) {
        this.date = date;
    }

    public KeywordRequest getKeywordRequest() {
        return keywordRequest;
    }

    public void setKeywordRequest(KeywordRequest keywordRequest) {
        this.keywordRequest = keywordRequest;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
