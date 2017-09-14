package com.topcom.cms.yuqing.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.cms.data.domain.News;
import com.topcom.cms.yuqing.domain.CustomSubject;
import com.topcom.cms.yuqing.domain.WarningTask;
import org.springframework.data.domain.Page;

public interface WarningManager extends GenericManager<WarningTask, Long> {

    @Deprecated
    Page<News> getSubjectWarningInfo(CustomSubject subject, int page, int limit);
}
