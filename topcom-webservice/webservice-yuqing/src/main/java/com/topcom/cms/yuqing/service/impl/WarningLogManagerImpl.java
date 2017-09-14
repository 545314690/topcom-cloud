package com.topcom.cms.yuqing.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.cms.common.page.DateParam;
import com.topcom.cms.common.page.Order;
import com.topcom.cms.common.page.PageRequest;
import com.topcom.cms.common.utils.DateUtil;
import com.topcom.cms.es.service.NewsService;
import com.topcom.cms.es.vo.BoolQueryRequest;
import com.topcom.cms.es.vo.KV;
import com.topcom.cms.es.vo.Keyword;
import com.topcom.cms.yuqing.dao.WarningLogDao;
import com.topcom.cms.yuqing.domain.CustomSubject;
import com.topcom.cms.yuqing.domain.WarningLog;
import com.topcom.cms.yuqing.domain.WarningTask;
import com.topcom.cms.yuqing.service.WarningLogManager;
import org.apache.commons.lang.time.DateUtils;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service(value = "warningLogManager")
@Transactional
public class WarningLogManagerImpl extends GenericManagerImpl<WarningLog, Long>
        implements WarningLogManager {
    WarningLogDao warningLogDao;

    @Autowired
    private NewsService newsService;

    @Autowired
    public void setWarningLogDao(WarningLogDao warningLogDao) {
        this.warningLogDao = warningLogDao;
        this.dao = this.warningLogDao;
    }

    @Override
    public WarningLog formSubject(CustomSubject subject) {
        if (subject == null) {
            return null;
        }
        WarningTask warningTask = subject.getWarning();
        if (warningTask == null) {
            return null;
        }
        WarningLog warningLog = new WarningLog();
        warningLog.setUserId(subject.getUserId());
        warningLog.setSubjectName(subject.getName());
        warningLog.setSubjectId(subject.getId());
        warningLog.setMustWord(subject.getMustWord());
        warningLog.setShouldWord(subject.getShouldWord());
        warningLog.setMustNotWord(subject.getMustNotWord());
        //本次预警时间
        Date thisWarningDate = new Date();
        //上次预警时间
        Date lastWarningDate = warningTask.getLastWarningDate();

        if (lastWarningDate == null) {
            lastWarningDate = subject.getStartDate();
        }
        warningLog.setStartDate(DateUtil.dateToString(lastWarningDate));
        warningLog.setEndDate(DateUtil.dateToString(thisWarningDate));
        //媒体类型
        warningLog.setType(warningTask.getType());
        //情感
        warningLog.setSentimentLabel(warningTask.getSentimentLabel());
        warningLog.setWarningTaskId(warningTask.getId());
        warningLog.setField(warningTask.getFiled());
        return warningLog;
    }

    @Override
    public Page getWarningInfo(Long warningLogId, int pageNum, int limit) {
        WarningLog warningLog = warningLogDao.findOne(warningLogId);
        return getWarningInfo(warningLog, pageNum, limit);
    }

    @Override
    public Page getWarningInfo(WarningLog warningLog, int pageNum, int limit) {
        if (warningLog == null) {
            return new PageImpl<>(new ArrayList<>());
        }
        BoolQueryRequest request = new BoolQueryRequest();
        request.setType(null);
        request.setFiled(warningLog.getField());
        request.setKeyword(new Keyword(warningLog.getMustWord(), warningLog.getShouldWord(), warningLog.getMustNotWord()));

        /**
         * 数据发布时间:暂定三天内
         */
        //TODO:三天内合适不？
//        Date now = new Date();
//        request.setDate(new DateParam(DateUtil.dateToString(DateUtils.addDays(now, -3)), DateUtil.dateToString(now)));
        /**
         * 数据保存时间
         */
        request.setDateCreated(new DateParam(warningLog.getStartDate(), warningLog.getEndDate()));

        Date now = request.getDateCreated().endDate();
        request.setDate(new DateParam(DateUtil.dateToString(DateUtils.addDays(now, -3)), DateUtil.dateToString(now)));

        request.setPage(new PageRequest(pageNum, limit, new Order(Sort.Direction.DESC, "pubTime")));
        List<KV> kvList = new ArrayList<>();
        //媒体类型
        String[] type = warningLog.getType();
        if (type != null && type.length > 0) {
            request.setType(type);
        }
        String[] sentimentLabel = warningLog.getSentimentLabel();
        //情感类型
        if (sentimentLabel != null && sentimentLabel.length > 0) {
            kvList.add(new KV("nlp.sentiment.label", sentimentLabel));
        }
        request.setSearchKv(kvList);
        final Page page = newsService.findByMustShouldDateInType(request);
        return page;
    }

    @Override
    public Page<WarningLog> findBySubjectId(Long subjectId, Pageable pageable) {
        return warningLogDao.findBySubjectId(subjectId, pageable);
    }

    @Override
    public Page<WarningLog> findByUserId(Long userId, Pageable pageable) {
        return warningLogDao.findByUserId(userId, pageable);
    }

    @Override
    public Page<WarningLog> searchWarningLog(Long userId, String subjectName, Date startDate, Date endDate, Pageable pageable) {
        return warningLogDao.findByUserIdAndSubjectNameContainingAndDateCreatedBetween(userId, subjectName, startDate, endDate, pageable);
    }
}
