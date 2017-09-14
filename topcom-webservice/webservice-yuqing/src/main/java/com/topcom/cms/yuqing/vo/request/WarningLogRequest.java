package com.topcom.cms.yuqing.vo.request;

import com.topcom.cms.common.page.DateParam;
import com.topcom.cms.common.page.PageRequest;
import com.topcom.cms.yuqing.domain.Briefing;

import java.io.Serializable;

/**
 * Created by lism on 2017/5/17.
 */
public class WarningLogRequest implements Serializable {

    private PageRequest page = new PageRequest();
    private DateParam date = new DateParam();
    private String subjectName;
    private Long subjectId;

    public PageRequest getPage() {
        return page;
    }

    public void setPage(PageRequest page) {
        this.page = page;
    }

    public DateParam getDate() {
        return date;
    }

    public void setDate(DateParam date) {
        this.date = date;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
}
