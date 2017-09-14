package com.topcom.cms.yuqing.dao;

import com.topcom.cms.base.dao.GenericDao;
import com.topcom.cms.yuqing.domain.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactDao extends GenericDao<Contact, Long> {

    Page<Contact> findByUserId(Pageable pageable, Long userId);

    Page<Contact> findByUserIdAndType(Pageable pageable, Long userId, Contact.Type type);
}
