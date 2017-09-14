package com.topcom.cms.yuqing.vo.request;

import com.topcom.cms.common.page.PageRequest;

import java.io.Serializable;

/**
 * Created by topcom on 2017/8/23 0023.
 */
public class CustomSubjectRequest implements Serializable {

    private PageRequest page;
    private String subjectName;

    public PageRequest getPage() {
        return page;
    }

    public void setPage(PageRequest page) {
        this.page = page;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
