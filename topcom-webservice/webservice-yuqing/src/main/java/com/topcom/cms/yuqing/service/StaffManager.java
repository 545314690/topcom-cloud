package com.topcom.cms.yuqing.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.cms.yuqing.domain.Staff;
import com.topcom.cms.yuqing.domain.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StaffManager extends GenericManager<Staff, Long> {


    Page<Staff> findByOrganizationalStructuresName(Pageable pageable, String name, Long id);

    Page<Staff> findByName(Pageable pageable, String name, Long id);

    Page<Staff> findByOrganizationalStructureUserId(Pageable pageable, Long id);
    Page<Staff> findByOrganizationalStructureIdAndUserId(Pageable pageable, Long id, Long userId);
    List<Staff> findByOrganizationalStructureId(Long id);
}
