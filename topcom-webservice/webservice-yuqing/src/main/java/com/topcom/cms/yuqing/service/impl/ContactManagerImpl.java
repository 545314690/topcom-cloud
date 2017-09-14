package com.topcom.cms.yuqing.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.cms.yuqing.dao.ContactDao;
import com.topcom.cms.yuqing.domain.Contact;
import com.topcom.cms.yuqing.service.ContactManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "contactManager")
@Transactional
public class ContactManagerImpl extends GenericManagerImpl<Contact, Long>
        implements ContactManager {
    ContactDao contactDao;

    @Autowired
    public void setContactDao(ContactDao contactDao) {
        this.contactDao = contactDao;
        this.dao = this.contactDao;
    }

    @Override
    public Page<Contact> findByUserId(Pageable pageable, Long userId) {
        return contactDao.findByUserId(pageable, userId);
    }

    @Override
    public Page<Contact> findByUserIdAndType(Pageable pageable, Long userId, Contact.Type type) {
        return contactDao.findByUserIdAndType(pageable, userId, type);
    }

    @Cacheable(value = "cache_contact")
    @Override
    public Page<Contact> findAll(Pageable page) {
        return super.findAll(page);
    }
    @Cacheable(value = "cache_contact",key = "#id")
    @Override
    public Contact findById(Long id) {
        return super.findById(id);
    }

    @Override
    @CacheEvict(value = "cache_contact", allEntries = true)
    public void delete(Long id) {
        super.delete(id);
    }
}
