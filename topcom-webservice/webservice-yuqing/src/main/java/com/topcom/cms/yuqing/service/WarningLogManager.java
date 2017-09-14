package com.topcom.cms.yuqing.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.cms.data.domain.News;
import com.topcom.cms.yuqing.domain.CustomSubject;
import com.topcom.cms.yuqing.domain.WarningLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface WarningLogManager extends GenericManager<WarningLog, Long> {

    /**
     * 通过当前专题及预警规则生成 WarningLog
     *
     * @param subject
     * @return
     */
    public WarningLog formSubject(CustomSubject subject);

    public Page getWarningInfo(Long warningLogId, int pageNum, int limit);

    public Page getWarningInfo(WarningLog warningLog, int pageNum, int limit);

    Page<WarningLog> findBySubjectId(Long subjectId, Pageable pageable);

    Page<WarningLog> findByUserId(Long id, Pageable pageable);

    Page<WarningLog> searchWarningLog(Long userId, String subjectName, Date startDate, Date endDate, Pageable pageable);
}
