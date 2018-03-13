package com.topcom.cms.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.cms.domain.ViewLog;
import com.topcom.cms.vo.ViewLogDto;
import com.topcom.cms.vo.ViewLogRequest;

import java.util.List;


public interface ViewLogManager extends GenericManager<ViewLog, Long> {
    List<ViewLogDto> searchHotPage(ViewLogRequest request);
}
