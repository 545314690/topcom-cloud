package com.topcom.cms.yuqing.service.impl;

import com.topcom.cms.base.service.impl.GenericTreeManagerImpl;
import com.topcom.cms.yuqing.dao.LibraryDao;
import com.topcom.cms.yuqing.dao.OrganizationalStructureDao;
import com.topcom.cms.yuqing.domain.Library;
import com.topcom.cms.yuqing.domain.OrganizationalStructure;
import com.topcom.cms.yuqing.service.LibraryManager;
import com.topcom.cms.yuqing.service.OrganizationalStructureManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "organizationalStructureManager")
@Transactional
public class OrganizationalStructureManagerImpl extends GenericTreeManagerImpl<OrganizationalStructure, Long>
        implements OrganizationalStructureManager {
    OrganizationalStructureDao organizationalStructureDao;

    @Autowired
    public void setOrganizationalStructureDao(OrganizationalStructureDao organizationalStructureDao) {
        this.organizationalStructureDao = organizationalStructureDao;
        this.treeDao = this.organizationalStructureDao;
        this.dao = organizationalStructureDao;
    }

    @Override
    public Page<OrganizationalStructure> findByUserId(Pageable pageable, Long userId) {
        return organizationalStructureDao.findByUserId(pageable, userId);
    }

    @Override
    public Page<OrganizationalStructure> findByName(Pageable pageable,String name,Long userId) {
        return organizationalStructureDao.findByNameAndUserId(pageable,name,userId);
    }





}
