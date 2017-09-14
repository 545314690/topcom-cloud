package com.topcom.cms.yuqing.vo.request;

import com.topcom.cms.common.page.DateParam;
import com.topcom.cms.common.page.PageRequest;
import com.topcom.cms.yuqing.domain.Briefing;

import java.io.Serializable;

/**
 * Created by lism on 2017/5/17.
 */
public class BriefingRequest implements Serializable {

    private PageRequest page = new PageRequest();
    private DateParam date = new DateParam();
    private String title;

    private Briefing.BriefingType type;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Briefing.BriefingType getType() {
        return type;
    }

    public void setType(Briefing.BriefingType type) {
        this.type = type;
    }
}
