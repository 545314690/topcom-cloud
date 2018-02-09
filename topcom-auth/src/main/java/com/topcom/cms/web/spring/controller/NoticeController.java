package com.topcom.cms.web.spring.controller;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.domain.Notice;
import com.topcom.cms.service.NoticeManager;

/**
 * 系统公告管理维护的交互控制
 * 
 * @author lism
 * 
 */
@Controller
@RequestMapping("/admin/notice/")
public class NoticeController extends
		GenericController<Notice, Long, NoticeManager> {

	NoticeManager noticeManager;

	@Autowired
	public void setNoticeManager(NoticeManager noticeManager) {
		this.noticeManager = noticeManager;
		this.manager = this.noticeManager;
	}

	@Override
	@RequestMapping(value = { "/{id}" }, method = { org.springframework.web.bind.annotation.RequestMethod.DELETE }, produces = { "application/json" })
	@ResponseBody
	public void delete(@PathVariable Long id) throws IOException {
		this.manager.deleteNotice(id);
	}
}
