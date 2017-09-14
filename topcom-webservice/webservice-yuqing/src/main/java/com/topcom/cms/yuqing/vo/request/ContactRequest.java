package com.topcom.cms.yuqing.vo.request;

import com.topcom.cms.common.page.DateParam;
import com.topcom.cms.common.page.PageRequest;
import com.topcom.cms.yuqing.domain.Briefing;
import com.topcom.cms.yuqing.domain.Contact;

import java.io.Serializable;

/**
 * Created by lism on 2017/5/17.
 */
public class ContactRequest extends PageRequest {

    private Contact.Type type;

    public Contact.Type getType() {
        return type;
    }

    public void setType(Contact.Type type) {
        this.type = type;
    }
}
