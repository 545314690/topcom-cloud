package com.topcom.cms.yuqing.service;

import com.topcom.cms.base.service.GenericTreeManager;
import com.topcom.cms.yuqing.domain.Library;
import com.topcom.cms.yuqing.domain.OrganizationalStructure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrganizationalStructureManager extends GenericTreeManager<OrganizationalStructure, Long> {

    Page<OrganizationalStructure> findByUserId(Pageable pageable, Long userId);

    Page<OrganizationalStructure> findByName(Pageable pageable, String name, Long userId);

}
