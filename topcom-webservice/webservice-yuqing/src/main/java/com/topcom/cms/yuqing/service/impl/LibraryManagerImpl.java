package com.topcom.cms.yuqing.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.cms.base.service.impl.GenericTreeManagerImpl;
import com.topcom.cms.yuqing.dao.ContactDao;
import com.topcom.cms.yuqing.dao.LibraryDao;
import com.topcom.cms.yuqing.domain.Contact;
import com.topcom.cms.yuqing.domain.Library;
import com.topcom.cms.yuqing.domain.Word;
import com.topcom.cms.yuqing.service.ContactManager;
import com.topcom.cms.yuqing.service.LibraryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

@Service(value = "libraryManager")
@Transactional
public class LibraryManagerImpl extends GenericTreeManagerImpl<Library, Long>
        implements LibraryManager {
    LibraryDao libraryDao;

    @Autowired
    public void setLibraryDao(LibraryDao libraryDao) {
        this.libraryDao = libraryDao;
        this.treeDao = this.libraryDao;
        this.dao = libraryDao;
    }

    @Override
    public Page<Library> findByUserId(Pageable pageable, Long userId) {
        return libraryDao.findByUserId(pageable, userId);
    }

    @Override
    public Object findByName(Pageable pageable,String name,Long userId) {
        return libraryDao.findByNameAndUserId(pageable,name,userId);
    }

    @Override
    public Library findByIdAndUserId(Long id, Long userId) {
        return this.libraryDao.findByIdAndUserId(id,userId);
    }

    @Override
    public List<Library> getRootByUserId(Long userId) {
        return this.libraryDao.getRootByUserId(userId);
    }


}
