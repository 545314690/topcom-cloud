package com.topcom.cms.yuqing.dao;

import com.topcom.cms.mongo.base.BaseDao;
import com.topcom.cms.yuqing.domain.Briefing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface BriefingDao extends BaseDao<Briefing, String> {
    /**
     * 查询用户简报
     *
     * @param pageable
     * @param userId
     * @return
     */
    Page<Briefing> findByUserId(Pageable pageable, Long userId);


    Page<Briefing> findByUserIdAndTypeAndTitleLikeAndDateCreatedBetween(Pageable pageable, Long userId, Briefing.BriefingType type, String title,
                                                                        Date dateFrom, Date dateTo);

    Page<Briefing> findByTypeAndTitleLikeAndDateCreatedBetweenAndUserId(Pageable pageable, Briefing.BriefingType type, String title, Date dateFrom,
                                                                        Date dateTo, Long userId);

    List<Briefing> findBySubjectIdAndUserId(Long subjectId, Long userId);

    List<Briefing> findBySubjectId(Long subjectId);

    Page<Briefing> findByTypeAndTitleLikeAndDateCreatedBetweenAndSubjectIdAndUserId(Pageable pageable, Briefing.BriefingType type, String title, Date startDate, Date endDate, Long subjectId, Long userId);

    Page<Briefing> findBySubjectId(Long subjectId, Pageable pageable);
}
