package com.topcom.cms.yuqing.dao;

import com.topcom.cms.base.dao.GenericDao;
import com.topcom.cms.yuqing.domain.CustomSubject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CustomSubjectDao extends GenericDao<CustomSubject, Long> {

    /**
     * 查询用户自定义专题
     *
     * @param pageable
     * @param userId
     * @return
     */
    Page<CustomSubject> findByUserId(Pageable pageable, Long userId);

    CustomSubject findByUserIdAndName(Long id, String name);

    List<CustomSubject> findByEnableWarning(boolean enable);

    @Transactional
    @Modifying
    @Query("update CustomSubject u set u.state = ?2 where u.id = ?1")
    int updateState(Long id, CustomSubject.State state);

    Page<CustomSubject> findByUserIdAndNameLike(Pageable pageable, Long id, String subjectName);
}
