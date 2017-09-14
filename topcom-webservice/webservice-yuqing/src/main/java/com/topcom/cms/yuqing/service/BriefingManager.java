package com.topcom.cms.yuqing.service;

import com.mongodb.DBObject;
import com.topcom.cms.mongo.base.BaseService;
import com.topcom.cms.yuqing.domain.Briefing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * 简报管理业务层
 *
 * @author <a href="mailto:545314690@qq.om">lisenmiao</a>
 * @date 2014年10月11日下午3:42:41
 */
public interface BriefingManager extends BaseService<Briefing, String> {

    /**
     * 查询当前登录用户的简报
     *
     * @param pageable
     * @param userId
     * @return 分页数据
     */
    Page<Briefing> findByUserId(Pageable pageable, Long userId);

    /**
     * 根据简报标题、创建时间查询当前登录用户的简报
     *
     * @param pageable
     * @param userId
     * @param title    简报标题
     * @param dateFrom 开始时间
     * @param dateTo   截止时间
     * @return 分页数据
     */
    Page<Briefing> findByUserIdAndTypeAndTitleLikeAndDateCreatedBetween(Pageable pageable, Long userId, Briefing.BriefingType type, String title, Date dateFrom,
                                                                        Date dateTo);

    /**
     * 根据简报标题、创建时间查询简报
     *
     * @param pageable
     * @param title    简报标题
     * @param dateFrom 开始时间
     * @param dateTo   截止时间
     * @return 分页数据
     */
    Page<Briefing> findByTypeAndTitleLikeAndDateCreatedBetweenAndUserId(Pageable pageable, Briefing.BriefingType type, String title, Date dateFrom,
                                                                        Date dateTo, Long userId);

    /**
     * 查询简报详情，返回DBObject，因为需要json格式
     *
     * @param id
     * @return
     */
    DBObject findDetailById(String id);

    Object findDetailByType(String type);

    List<Briefing> findBySubjectIdAndUserId(Long subjectId, Long userId);

    List<Briefing> findBySubjectId(Long subjectId);

    Page<Briefing> findByTypeAndTitleLikeAndDateCreatedBetweenAndSubjectIdAndUserId(Pageable pageable, Briefing.BriefingType type, String title, Date startDate, Date endDate, Long subjectId, Long userId);

    Page<Briefing> findBySubjectId(Long subjectId, Pageable pageable);
}
