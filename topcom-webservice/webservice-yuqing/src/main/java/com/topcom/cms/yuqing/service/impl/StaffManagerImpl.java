package com.topcom.cms.yuqing.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.cms.yuqing.dao.StaffDao;
import com.topcom.cms.yuqing.dao.WordDao;
import com.topcom.cms.yuqing.domain.Staff;
import com.topcom.cms.yuqing.domain.Word;
import com.topcom.cms.yuqing.service.StaffManager;
import com.topcom.cms.yuqing.service.WordManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "staffManager")
@Transactional
public class StaffManagerImpl extends GenericManagerImpl<Staff, Long>
        implements StaffManager {
    StaffDao staffDao;

    @Autowired
    public void setStaffDao(StaffDao staffDao) {
        this.staffDao = staffDao;
        this.dao = this.staffDao;
    }


    @Override
    public Page<Staff> findByOrganizationalStructuresName(Pageable pageable, String name, Long id) {
        return staffDao.findByOrganizationalStructuresNameAndOrganizationalStructuresUserId(pageable,name,id);
    }

    @Override
    public Page<Staff> findByName(Pageable pageable, String name, Long id) {
        return staffDao.findByNameAndOrganizationalStructuresUserId(pageable,name,id);
    }

    @Override
    public Page<Staff> findByOrganizationalStructureUserId(Pageable pageable, Long id) {
        return staffDao.findByOrganizationalStructuresUserId(pageable,id);
    }

    @Override
    public Page<Staff> findByOrganizationalStructureIdAndUserId(Pageable pageable, Long id,Long userId) {
        return staffDao.findByOrganizationalStructuresIdAndOrganizationalStructuresUserId(pageable,id,userId);
    }

    @Override
    public List<Staff> findByOrganizationalStructureId(Long id) {
        return staffDao.findByOrganizationalStructuresId(id);
    }
}
