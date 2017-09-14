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
 * @author maodaqiang
 * 
 */
@Controller
@RequestMapping("/admin/notice/")
public class NoticeController extends
		GenericController<Notice, Long, NoticeManager> {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(NoticeController.class);

	NoticeManager noticeManager;

	@Autowired
	public void setNoticeManager(NoticeManager noticeManager) {
		this.noticeManager = noticeManager;
		this.manager = this.noticeManager;
	}

	@RequestMapping(value = { "/{id}" }, method = { org.springframework.web.bind.annotation.RequestMethod.DELETE }, produces = { "application/json" })
	@ResponseBody
	public void delete(@PathVariable Long id) throws IOException {
		if (logger.isInfoEnabled()) {
			logger.info("delete(Long) - Long id=" + id); //$NON-NLS-1$
		}
		this.manager.deleteNotice(id);
	}
	/*
	 * @RequestMapping(value ="imageUp.do", method =RequestMethod.POST) public
	 * void uploadImage(@RequestParam("upfile") MultipartFile
	 * upfile,HttpServletRequest request, HttpServletResponse response){ try {
	 * request.setCharacterEncoding("utf-8");
	 * 
	 * response.setCharacterEncoding("utf-8"); Uploader up = new
	 * Uploader(request); up.setSavePath("../img"); String[] fileType = {".gif"
	 * , ".png" , ".jpg" , ".jpeg" , ".bmp"}; up.setAllowFiles(fileType);
	 * up.setMaxSize(10000); //单位KB up.upload();
	 * response.getWriter().print("{'original':'"
	 * +up.getOriginalName()+"','url':'"
	 * +up.getUrl()+"','title':'"+up.getTitle()+
	 * "','state':'"+up.getState()+"'}"); } catch (Exception e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } }
	 */

}
