package com.topcom.cms.yuqing.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.cms.common.page.DateParam;
import com.topcom.cms.common.page.Order;
import com.topcom.cms.common.page.PageRequest;
import com.topcom.cms.common.utils.DateUtil;
import com.topcom.cms.data.domain.News;
import com.topcom.cms.es.service.NewsService;
import com.topcom.cms.es.vo.BoolQueryRequest;
import com.topcom.cms.es.vo.KV;
import com.topcom.cms.es.vo.Keyword;
import com.topcom.cms.yuqing.dao.WarningTaskDao;
import com.topcom.cms.yuqing.domain.CustomSubject;
import com.topcom.cms.yuqing.domain.WarningTask;
import com.topcom.cms.yuqing.service.WarningManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service(value = "warningManager")
@Transactional
public class WarningManagerImpl extends GenericManagerImpl<WarningTask, Long>
        implements WarningManager {
    WarningTaskDao warningDao;

    @Autowired
    private NewsService newsService;

    @Autowired
    public void setWarningDao(WarningTaskDao warningDao) {
        this.warningDao = warningDao;
        this.dao = this.warningDao;
    }

    @Deprecated
    @Override
    public Page<News> getSubjectWarningInfo(CustomSubject subject, int pageNum, int limit) {
        WarningTask warning = subject.getWarning();
        if (warning == null) {
            return new PageImpl<News>(new ArrayList<News>());
        }
        BoolQueryRequest request = new BoolQueryRequest();
        request.setType(null);
        request.setFiled(null);
        request.setKeyword(new Keyword(subject.getMustWord(), subject.getShouldWord(), subject.getMustNotWord()));
        //本次预警时间
        Date thisWarningDate = new Date();
        //上次预警时间
        Date lastWarningDate = warning.getLastWarningDate();
        if (lastWarningDate == null) {
            lastWarningDate = subject.getStartDate();
        }
        request.setDate(new DateParam(DateUtil.dateToString(lastWarningDate), DateUtil.dateToString(thisWarningDate)));
        request.setPage(new PageRequest(pageNum, limit, new Order(Sort.Direction.DESC, "pubTime")));
        List<KV> kvList = new ArrayList<>();
        //媒体类型
        String[] type = warning.getType();
        if (type != null && type.length > 0) {
            kvList.add(new KV("type", type));
        }
        String[] sentimentLabel = warning.getSentimentLabel();
        //情感类型
        if (sentimentLabel != null && sentimentLabel.length != 0) {
            kvList.add(new KV("nlp.sentiment.label", sentimentLabel));
        }
        request.setSearchKv(kvList);
        final Page page = newsService.findByMustShouldDateInType(request);
        return page;
    }
}
