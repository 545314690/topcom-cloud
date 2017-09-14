package com.topcom.cms.yuqing.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.cms.yuqing.domain.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactManager extends GenericManager<Contact, Long> {

    Page<Contact> findByUserId(Pageable pageable, Long userId);

    Page<Contact> findByUserIdAndType(Pageable pageable, Long id, Contact.Type type);
}
