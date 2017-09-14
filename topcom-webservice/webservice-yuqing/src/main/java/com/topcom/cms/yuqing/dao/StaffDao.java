package com.topcom.cms.yuqing.dao;

import com.topcom.cms.base.dao.GenericDao;
import com.topcom.cms.yuqing.domain.Staff;
import com.topcom.cms.yuqing.domain.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StaffDao extends GenericDao<Staff, Long> {

    Page<Staff> findByOrganizationalStructuresIdAndOrganizationalStructuresUserId(Pageable pageable, Long id, Long userId);

    Page<Staff> findByOrganizationalStructuresNameAndOrganizationalStructuresUserId(Pageable pageable, String name, Long id);

    Page<Staff> findByNameAndOrganizationalStructuresUserId(Pageable pageable, String name, Long userId);

    Page<Staff> findByOrganizationalStructuresUserId(Pageable pageable, Long id);

    List<Staff> findByOrganizationalStructuresId(Long id);
}
