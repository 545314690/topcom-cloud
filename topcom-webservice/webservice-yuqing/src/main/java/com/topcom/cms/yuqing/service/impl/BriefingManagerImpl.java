package com.topcom.cms.yuqing.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.topcom.cms.mongo.base.BaseServiceImpl;
import com.topcom.cms.utils.MongoDBUtil;
import com.topcom.cms.utils.MongoResultUtil;
import com.topcom.cms.yuqing.config.Constants;
import com.topcom.cms.yuqing.dao.BriefingDao;
import com.topcom.cms.yuqing.domain.Briefing;
import com.topcom.cms.yuqing.service.BriefingManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 简报业务层实现
 *
 * @author <a href="mailto:545314690@qq.om">lisenmiao</a>
 * @date 2014年10月11日下午3:44:17
 */
@Service(value = "briefingManager")
@Transactional
public class BriefingManagerImpl extends BaseServiceImpl<Briefing, String> implements
        BriefingManager {

    BriefingDao briefingDao;

    @Autowired
    public void setBriefingDao(BriefingDao briefingDao) {
        this.briefingDao = briefingDao;
        this.baseDao = this.briefingDao;
    }

    @Override
    public Page<Briefing> findByUserId(Pageable pageable, Long userId) {
        return briefingDao.findByUserId(pageable, userId);
    }

    @Override
    public Page<Briefing> findByUserIdAndTypeAndTitleLikeAndDateCreatedBetween(Pageable pageable, Long userId, Briefing.BriefingType type, String title,
                                                                               Date dateFrom, Date dateTo) {
        return briefingDao.findByUserIdAndTypeAndTitleLikeAndDateCreatedBetween(pageable, userId, type, title, dateFrom, dateTo);
    }

    @Override
    public Page<Briefing> findByTypeAndTitleLikeAndDateCreatedBetweenAndUserId(Pageable pageable, Briefing.BriefingType type, String title, Date dateFrom,
                                                                      Date dateTo,Long userId) {
        return briefingDao.findByTypeAndTitleLikeAndDateCreatedBetweenAndUserId(pageable, type, title, dateFrom, dateTo,userId);
    }

    @Override
    public DBObject findDetailById(String id) {
        Object object = MongoResultUtil.handle_Id(MongoDBUtil.findById(Constants.Mongo.COLLECTION_BRIEFING, id));
        if (object != null) {
            return (DBObject) object;
        } else {
            return new BasicDBObject();
        }
    }

    @Override
    public Object findDetailByType(String type) {
        List<DBObject> dbObjectList = MongoDBUtil.selectDocument(Constants.Mongo.COLLECTION_BRIEFING, new BasicDBObject().append("type", type));
        return dbObjectList;
    }

    @Override
    public List<Briefing> findBySubjectIdAndUserId(Long subjectId, Long userId) {
        return briefingDao.findBySubjectIdAndUserId(subjectId, userId);
    }

    @Override
    public List<Briefing> findBySubjectId(Long subjectId) {
        return briefingDao.findBySubjectId(subjectId);
    }

    @Override
    public Page<Briefing> findByTypeAndTitleLikeAndDateCreatedBetweenAndSubjectIdAndUserId(Pageable pageable, Briefing.BriefingType type, String title, Date startDate, Date endDate, Long subjectId,Long userId) {
        return briefingDao.findByTypeAndTitleLikeAndDateCreatedBetweenAndSubjectIdAndUserId(pageable, type, title, startDate, endDate, subjectId,userId);
    }

    @Override
    public Page<Briefing> findBySubjectId(Long subjectId, Pageable pageable) {
        return briefingDao.findBySubjectId(subjectId, pageable);
    }

}
