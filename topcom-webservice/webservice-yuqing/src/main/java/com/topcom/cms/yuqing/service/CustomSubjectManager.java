package com.topcom.cms.yuqing.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.cms.yuqing.domain.CustomSubject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 自定义专题业务层接口
 *
 * @author <a href="mailto:545314690@qq.om">lisenmiao</a>
 * @date 2014年9月12日上午10:13:58
 */
public interface CustomSubjectManager extends
        GenericManager<CustomSubject, Long> {

    /**
     * 查询当前登录用户的自定义专题
     *
     * @param pageable
     * @param userId
     * @return 分页数据
     */
    Page<CustomSubject> findByUserId(Pageable pageable, Long userId);

    CustomSubject findByUserIdAndName(Long id, String name);


    List<CustomSubject> findByEnableWarning(boolean enable);

    boolean checkEnableWarning(CustomSubject subject);

    int updateState(Long id, CustomSubject.State completed);

    Page<CustomSubject> findByUserIdAndNameLike(Pageable pageable, Long id, String subjectName);
}
