package com.topcom.cms.yuqing.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.common.page.PageRequest;
import com.topcom.cms.domain.User;
import com.topcom.cms.web.bind.annotation.CurrentUser;
import com.topcom.cms.yuqing.domain.Contact;
import com.topcom.cms.yuqing.service.ContactManager;
import com.topcom.cms.yuqing.vo.request.ContactRequest;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/contact/")
@Api("预警联系人接口")
public class ContactController extends
        GenericController<Contact, Long, ContactManager> {
    ContactManager contactManager;

    @Autowired
    public void setContactManager(ContactManager contactManager) {
        this.contactManager = contactManager;
        this.manager = contactManager;
    }

    /**
     * 查询当前登录用户的联系人
     *
     * @return 分页数据
     */
    @RequestMapping(value = "/findByUser", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Page<Contact> findByUserId(@CurrentUser User user, @RequestBody com.topcom.cms.common.page.PageRequest pageRequest) {
        return contactManager.findByUserId(pageRequest.pageable(), user.getId());
    }

    /**
     * 根据类型查询当前登录用户的联系人
     *
     * @return 分页数据
     */
    @RequestMapping(value = "/findByType", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Page<Contact> findByType(@CurrentUser User user, @RequestBody ContactRequest pageRequest) {
        Contact.Type type = pageRequest.getType();
        if (type==null|| StringUtils.isEmpty(type)){
            return this.contactManager.findByUserId(pageRequest.pageable(),user.getId());
        }
        return contactManager.findByUserIdAndType(pageRequest.pageable(), user.getId(), pageRequest.getType());
    }

}
