package com.topcom.cms.service;

import org.springframework.transaction.annotation.Transactional;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.cms.domain.Notice;

/**
 * 系统公告信息接口
 * 
 * @author maodaqiang
 * 
 */
@Transactional
public interface NoticeManager extends GenericManager<Notice, Long> {

	/**
	 * 通过ID 删除相应登录日志
	 * 
	 * @param id
	 */
	void deleteNotice(Long id);

}
