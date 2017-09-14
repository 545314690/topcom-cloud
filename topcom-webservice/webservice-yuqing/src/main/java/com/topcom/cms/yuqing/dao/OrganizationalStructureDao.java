package com.topcom.cms.yuqing.dao;

import com.topcom.cms.base.dao.GenericTreeDao;
import com.topcom.cms.yuqing.domain.OrganizationalStructure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrganizationalStructureDao extends GenericTreeDao<OrganizationalStructure, Long>
{

    Page<OrganizationalStructure> findByUserId(Pageable pageable, Long userId);

    Page<OrganizationalStructure> findByNameAndUserId(Pageable pageable, String name, Long userId);


}
