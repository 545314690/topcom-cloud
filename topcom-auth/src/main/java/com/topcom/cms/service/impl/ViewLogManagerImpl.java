package com.topcom.cms.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.cms.dao.ViewLogDao;
import com.topcom.cms.domain.ViewLog;
import com.topcom.cms.service.ViewLogManager;
import com.topcom.cms.vo.ViewLogDto;
import com.topcom.cms.vo.ViewLogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 资源访问实现类
 * 
 * @author lism
 * 
 */
@Service(value = "viewLogManager")
@Transactional
public class ViewLogManagerImpl extends GenericManagerImpl<ViewLog, Long>
implements ViewLogManager {

	ViewLogDao viewLogDao;

	@Autowired
	public void setViewLogDao(ViewLogDao viewLogDao) {
		this.viewLogDao = viewLogDao;
		this.dao = this.viewLogDao;
	}

	@Override
	public List<ViewLogDto> searchHotPage(ViewLogRequest request) {
		StringBuffer s = new StringBuffer();
		s.append("(");
		for (Long l:request.getResourceIds()){
			s.append(l);
			s.append(",");
		}
		if (s.length()>1){
			s.deleteCharAt(s.length()-1);
		}
		s.append(")");
		return this.viewLogDao.findByResourceIdInAndGroupByResourceNameAndResourceId(request.getResourceIds());
	}
}
