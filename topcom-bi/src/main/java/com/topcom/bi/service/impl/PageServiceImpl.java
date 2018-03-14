package com.topcom.bi.service.impl;

import com.topcom.bi.dao.PageDao;
import com.topcom.bi.domain.Page;
import com.topcom.bi.service.PageService;
import com.topcom.cms.mongo.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lism on 17-10-31.
 *
 * @author lism
 */
@Service
@Transactional
public class PageServiceImpl extends BaseServiceImpl<Page, String> implements PageService {
    private PageDao pageDao;

    @Autowired
    public void setPageDao(PageDao pageDao) {
        this.pageDao = pageDao;
        this.baseDao = pageDao;
    }
    @Override
    public org.springframework.data.domain.Page<Page> findByNameLike(String name, Pageable pageable) {
        return pageDao.findByNameLike(name, pageable) ;
    }
}
