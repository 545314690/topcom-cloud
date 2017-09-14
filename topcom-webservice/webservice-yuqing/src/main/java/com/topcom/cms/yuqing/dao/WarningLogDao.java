package com.topcom.cms.yuqing.dao;

import com.topcom.cms.base.dao.GenericDao;
import com.topcom.cms.yuqing.domain.WarningLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface WarningLogDao extends GenericDao<WarningLog, Long> {

    Page<WarningLog> findBySubjectId(Long subjectId, Pageable pageable);

    Page<WarningLog> findByUserId(Long userId, Pageable pageable);

//    Page<WarningLog> searchWarningLog(Long userId, String subjectName, Date startDate, Date endDate, Pageable pageable);

    Page<WarningLog> findByUserIdAndSubjectNameContainingAndDateCreatedBetween(Long userId, String subjectName, Date startDate, Date endDate, Pageable pageable);
}
